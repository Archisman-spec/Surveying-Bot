package com.Surveying_Bot.controllers;

import com.Surveying_Bot.models.BinStatus;
import com.Surveying_Bot.payloads.BinRequestDTO;
import com.Surveying_Bot.payloads.BinResponseDTO;
import com.Surveying_Bot.service.BinService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/bins")
@RequiredArgsConstructor
public class BinController {

    private final BinService binService;

    @PostMapping
    public ResponseEntity<BinResponseDTO> createBin(@Valid @RequestBody BinRequestDTO binRequestDTO){
        BinResponseDTO createdBin = binService.createBin(binRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdBin);
    }

    @GetMapping
    public ResponseEntity<BinResponseDTO> getUserBin(@RequestParam("userId") UUID userId){
        return ResponseEntity.ok(binService.getUserBin(userId));
    }

    @GetMapping("/above-80")
    public ResponseEntity<List<BinResponseDTO>> getBinsAboveEighty(){
        return ResponseEntity.ok(binService.getBinsWithLevelAbove(80L));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteOldImages(@RequestParam("userId") UUID userId){
        binService.deleteOldImages(userId);

        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "Old Images deleted (kept first 10)"
        ));
    }

    @PatchMapping("/status")
    public ResponseEntity<BinResponseDTO> updateBinStatus(@RequestParam("userId") UUID userId,
                                                          @RequestParam("status")BinStatus newStatus){

        return ResponseEntity.ok(binService.updateBinStatus(userId, newStatus));
    }

}
