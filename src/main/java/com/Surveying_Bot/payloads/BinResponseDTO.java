package com.Surveying_Bot.payloads;

import com.Surveying_Bot.models.BinStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BinResponseDTO {
    private UUID binId;
    private Long binLevel;
    private BinStatus binStatus;
    private String imageUrl;
    private OffsetDateTime createdAt;
    private UUID userId;

}
