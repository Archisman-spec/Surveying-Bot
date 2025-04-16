package com.Surveying_Bot.payloads;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BinRequestDTO {

    @NotNull(message = "User ID is required")
    private UUID userId;

    @NotNull(message = "Bin level must not be null")
    @Min(value = 0, message = "Bin level must be at least 0%")
    @Max(value = 100, message = "Bin level must not exceed 100%")
    private Long binLevel;

    @NotBlank(message = "Image URL must not be blank")
    @Size(max = 2048, message = "Image URL is too long")
    private String imageUrl;

}
