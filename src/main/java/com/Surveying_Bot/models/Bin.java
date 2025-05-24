package com.Surveying_Bot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "bin")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bin {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private OffsetDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "bin_status")
    private BinStatus binStatus;

    @NotBlank(message = "Image URL must not be blank")
    @Size(max = 2048, message = "Image URL is too long")
    @Column(nullable = false, length = 2048)
    private String imageUrl;

    @Min(value = 0, message = "Bin level must be at least 0%")
    @Max(value = 100, message = "Bin level must not exceed 100%")
    @Column(nullable = false)
    private Long binLevel;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id", nullable = false, unique = true)
    private Users users;

}

