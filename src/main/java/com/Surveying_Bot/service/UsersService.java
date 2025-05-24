package com.Surveying_Bot.service;

import com.Surveying_Bot.payloads.UsersRequestDTO;
import com.Surveying_Bot.payloads.UsersResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UsersService {

    UsersResponseDTO createUser(UsersRequestDTO usersRequestDTO);

    UsersResponseDTO getUserById(UUID id);

    List<UsersResponseDTO> getAllUsers();

    UsersResponseDTO updateUser(UUID id, UsersRequestDTO usersRequestDTO);

    String deleteUser(UUID id);



}
