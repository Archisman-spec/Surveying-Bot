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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BinStatus binStatus;

    @NotBlank(message = "Image URL must not be blank")
    @Size(max = 2048, message = "Image URL is too long")
    private String imageUrl;

    @NotNull(message = "Bin level must not be null")
    @Min(value = 0, message = "Bin level must be at least 0%")
    @Max(value = 100, message = "Bin level must not exceed 100%")
    private Long binLevel;

    @OneToOne
    @JoinColumn(name = "id",referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    private Users users;


}
