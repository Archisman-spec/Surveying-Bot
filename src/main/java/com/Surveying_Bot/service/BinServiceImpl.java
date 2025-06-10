package com.Surveying_Bot.service;

import com.Surveying_Bot.exceptions.APIException;
import com.Surveying_Bot.exceptions.ResourceNotFoundException;
import com.Surveying_Bot.models.Bin;
import com.Surveying_Bot.models.BinStatus;
import com.Surveying_Bot.models.Users;
import com.Surveying_Bot.payloads.BinRequestDTO;
import com.Surveying_Bot.payloads.BinResponseDTO;
import com.Surveying_Bot.repository.BinRepo;
import com.Surveying_Bot.repository.UsersRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class BinServiceImpl implements BinService{

    private final BinRepo binRepo;

    private final ModelMapper modelMapper;

    @Override
    public BinResponseDTO updateBin(UUID userId, BinRequestDTO binRequestDTO) {
        Bin existingBin = binRepo.findBinByUsersId(userId)
                .orElseThrow(()-> new ResourceNotFoundException("Bin", "userId", userId));

        if (binRequestDTO.getBinLevel() != null) {
            existingBin.setBinLevel(binRequestDTO.getBinLevel());
        }

        if (binRequestDTO.getImageUrls() != null){
            existingBin.setImageUrls(new ArrayList<>(binRequestDTO.getImageUrls()));
        }

        Bin updatedBin = binRepo.save(existingBin);

        return mapToBinResponseDTO(updatedBin);
    }

    @Override
    public BinResponseDTO getUserBin(UUID userId) {
        Bin bin = binRepo.findBinByUsersId(userId)
                .orElseThrow(()-> new ResourceNotFoundException("Bin", "userId", userId));

        return mapLimitedImages(bin);
    }

    @Override
    public BinResponseDTO updateBinStatus(UUID userId, BinStatus newStatus) {
        Bin bin = binRepo.findBinByUsersId(userId)
                .orElseThrow(()-> new ResourceNotFoundException("Bin", "userId", userId));

        if (bin.getBinStatus() == newStatus){
            throw new APIException("Bin is already in status: " + newStatus);
        }

        bin.setBinStatus(newStatus);
        Bin updatedBin = binRepo.save(bin);
        return mapToBinResponseDTO(updatedBin);
    }

    @Override
    public void deleteOldImages(UUID userId) {
        Bin bin = binRepo.findBinByUsersId(userId)
                .orElseThrow(()-> new ResourceNotFoundException("Bin", "userId", userId));

        if (bin.getImageUrls() != null && bin.getImageUrls().size() > 10 ){
            bin.setImageUrls( new ArrayList<>(bin.getImageUrls().subList(0,10)));
        }

        binRepo.save(bin);
    }

    @Override
    public List<BinResponseDTO> getBinsWithLevelAbove(Long level) {
        List<Bin> bins = binRepo.findByBinLevelGreaterThan(level);
        return bins.stream().
                map(bin -> mapToBinResponseDTO(bin))
                .collect(Collectors.toList());

    }

    private BinResponseDTO mapToBinResponseDTO(Bin bin){
        BinResponseDTO dto = modelMapper.map(bin, BinResponseDTO.class);
        dto.setUserId(bin.getUsers().getId());
        return dto;
    }

    private BinResponseDTO mapLimitedImages(Bin bin){
        BinResponseDTO dto = mapToBinResponseDTO(bin);
        if (dto.getImageUrls() != null && dto.getImageUrls().size() > 10 ){
            dto.setImageUrls(new ArrayList<>(dto.getImageUrls().subList(0,10)));
        }
        return dto;
    }

}

