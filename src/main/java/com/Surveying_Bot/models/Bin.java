package com.Surveying_Bot.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "bin")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "created_at", updatable = false, insertable = false)
    private OffsetDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "bin_status", nullable = false, length = 20)
    private BinStatus binStatus;

    @Column(name = "image_url", columnDefinition = "text[]", nullable = false)
    private List<String> imageUrls = new ArrayList<>();

    @Min(value = 0, message = "Bin level must be at least 0%")
    @Max(value = 100, message = "Bin level must not exceed 100%")
    @Column(nullable = false)
    private Long binLevel;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "id", nullable = false, unique = true)
    private Users users;

}

