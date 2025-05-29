package com.Surveying_Bot.service;

import com.Surveying_Bot.models.BinStatus;
import com.Surveying_Bot.payloads.BinRequestDTO;
import com.Surveying_Bot.payloads.BinResponseDTO;

import java.util.List;
import java.util.UUID;

public interface BinService {

    BinResponseDTO createBin(BinRequestDTO binRequestDTO);

    BinResponseDTO getUserBin(UUID userId);

    void deleteOldImages(UUID userId);

    BinResponseDTO updateBinStatus(UUID userId, BinStatus newStatus);

    List<BinResponseDTO> getBinsWithLevelAbove(Long level);

}
