package com.Surveying_Bot.models;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    private UUID id;

    private OffsetDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "role")
    private Role role;

    @NotBlank(message = "Username must not be null")
    @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_]{3,30}$",message = "Username must contain only letters, digits, or underscores")
    @Column(nullable = false, unique = true, length = 30)
    private String username;

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Bin bin;

}
