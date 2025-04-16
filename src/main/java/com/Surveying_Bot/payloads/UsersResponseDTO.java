package com.Surveying_Bot.payloads;

import com.Surveying_Bot.models.BinStatus;
import com.Surveying_Bot.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersResponseDTO {
    private UUID userId;
    private String username;
    private Role role;

    private UUID binId;
    private BinStatus binStatus;

}
