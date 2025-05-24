package com.Surveying_Bot.repository;

import com.Surveying_Bot.models.Bin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BinRepo extends JpaRepository<Bin, UUID> {
    List<Bin> findByBinLevelGreaterThan(Long level);

    Optional<Bin> findBinByUsersId(UUID userId);

}

