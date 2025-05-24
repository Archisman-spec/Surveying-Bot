package com.Surveying_Bot.payloads;

import com.Surveying_Bot.models.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersRequestDTO {

    @NotNull(message = "User ID is required")
    private UUID id;

    @NotBlank(message = "Username must not be empty")
    @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_]{3,30}$", message = "Username must contain only letters, digits, or underscores")
    private String username;

    @NotNull(message = "Role is required")
    private Role role;

}
