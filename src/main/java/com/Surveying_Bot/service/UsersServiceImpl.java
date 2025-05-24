package com.Surveying_Bot.service;
import com.Surveying_Bot.exceptions.APIException;
import com.Surveying_Bot.exceptions.ResourceNotFoundException;
import com.Surveying_Bot.models.Bin;
import com.Surveying_Bot.models.BinStatus;
import com.Surveying_Bot.models.Role;
import com.Surveying_Bot.models.Users;
import com.Surveying_Bot.payloads.UsersRequestDTO;
import com.Surveying_Bot.payloads.UsersResponseDTO;
import com.Surveying_Bot.repository.BinRepo;
import com.Surveying_Bot.repository.UsersRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepo userRepo;

 //   private final BinRepo binRepo;

    private final ModelMapper modelMapper;

    @Override
    public UsersResponseDTO createUser(UsersRequestDTO usersRequestDTO) {

        try {
            if (usersRequestDTO.getUsername() == null || usersRequestDTO.getUsername().trim().isEmpty()) {
                throw new APIException("Username cannot be empty");
            }

            if (userRepo.findByUsername(usersRequestDTO.getUsername()).isPresent()) {
                throw new APIException("Username already exists!");
            }

            Users users = modelMapper.map(usersRequestDTO, Users.class);

            users.setId(usersRequestDTO.getId());

            users.setRole(usersRequestDTO.getRole());

            Users savedUser = userRepo.save(users);

//            Bin bin = new Bin();
//            bin.setUsers(savedUser);
//            bin.setBinStatus(BinStatus.PENDING);
//            binRepo.save(bin);

            return modelMapper.map(savedUser, UsersResponseDTO.class);

        } catch (DataIntegrityViolationException e){
            throw new APIException("A User with this username already exists.", e);
        }
    }

    @Override
    public UsersResponseDTO getUserById(UUID id) {
        Users user = userRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User", "userId", id));

        return modelMapper.map(user, UsersResponseDTO.class);
    }

    @Override
    public List<UsersResponseDTO> getAllUsers() {
        List<Users> users = userRepo.findAll();

        return users.stream().map(user-> modelMapper.map(user, UsersResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UsersResponseDTO updateUser(UUID id, UsersRequestDTO usersRequestDTO) {
        Users existingUser = userRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User", "userId", id));

        if(usersRequestDTO.getUsername() != null && !usersRequestDTO.getUsername().isBlank()){
            if (userRepo.existsByUsernameAndIdNot(usersRequestDTO.getUsername(), id)){
                throw new APIException("Username is already taken by another user");
            }
            existingUser.setUsername(usersRequestDTO.getUsername());
        }

        if (usersRequestDTO.getRole() != null) {
            existingUser.setRole(usersRequestDTO.getRole());
        }

        Users updatedUser = userRepo.save(existingUser);

        return modelMapper.map(updatedUser, UsersResponseDTO.class);
    }

    @Override
    public String deleteUser(UUID id) {
        Users user = userRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User", "userId", id));

      //  binRepo.findBinByUsersId(user.getId())
           //     .ifPresent(bin -> binRepo.delete(bin));

        userRepo.delete(user);

        return "User with userId " + id + " deleted successfully!";
    }

}


