package com.Surveying_Bot.payloads;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
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

    @NotEmpty(message = "Image URLs must not be empty")
    private List<String> imageUrls;

}
