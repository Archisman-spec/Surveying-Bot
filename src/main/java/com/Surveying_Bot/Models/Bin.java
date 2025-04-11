package com.Surveying_Bot.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "bin")
@AllArgsConstructor
@NoArgsConstructor
public class Bin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private OffsetDateTime createdAt = OffsetDateTime.now();

    @NotNull(message = "Image URL must not be null")
    @Size(min = 10, max = 500, message = "Image URL must be between 10 and 500 characters")
    @Pattern(regexp = "^(https?://).+", message = "Image URL must be a valid HTTP or HTTPS URL")
    private String imageUrl;

    @NotNull(message = "Bin level must not be null")
    @PositiveOrZero(message = "Bin level must be zero or positive")
    private Long binLevel;

    @OneToOne
    @JoinColumn(name = "id",referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    private Users users;


}
