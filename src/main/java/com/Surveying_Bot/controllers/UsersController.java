package com.Surveying_Bot.controllers;

import com.Surveying_Bot.payloads.UsersRequestDTO;
import com.Surveying_Bot.payloads.UsersResponseDTO;
import com.Surveying_Bot.service.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PostMapping
    public ResponseEntity<UsersResponseDTO> createUser(@Valid @RequestBody UsersRequestDTO usersRequestDTO){
        UsersResponseDTO createdUser = usersService.createUser(usersRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersResponseDTO> getUserById(@PathVariable("id") UUID id){
        UsersResponseDTO user = usersService.getUserById(id);

        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<UsersResponseDTO>> getAllUsers(){
        List<UsersResponseDTO> users = usersService.getAllUsers();

        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsersResponseDTO> updateUser(@PathVariable("id") UUID id, @Valid @RequestBody UsersRequestDTO usersRequestDTO){
        UsersResponseDTO updatedUser = usersService.updateUser(id, usersRequestDTO);

        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") UUID id){
        usersService.deleteUser(id);

        return ResponseEntity.ok("Users Deleted Successfully!");
    }
    
}
