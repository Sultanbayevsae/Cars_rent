package org.example.server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.server.dto.PaymentCardRequestDTO;
import org.example.server.dto.PaymentCardResponseDTO;
import org.example.server.service.PaymentCardService;
import org.example.server.utill.AppConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(AppConstants.BASE_URL + "/cards")
@Tag(
        name = "Payment Card Controller",
        description = "APIs for payment card management"
)
@RequiredArgsConstructor
public class PaymentCardController {

    private final PaymentCardService cardService;

    @PostMapping
    @Operation(summary = "Create Payment Card for user")
    public ResponseEntity<PaymentCardResponseDTO> createCard(@Valid @RequestBody PaymentCardRequestDTO dto) {
        return ResponseEntity.ok(cardService.createCard(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Payment Card by user ID")
    public ResponseEntity<PaymentCardResponseDTO> getCard(@PathVariable UUID id) {
        return ResponseEntity.ok(cardService.getCardById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PaymentCardResponseDTO>> getCardsByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(cardService.getCardsByUserId(userId));
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<PaymentCardResponseDTO> deactivateCard(@PathVariable UUID id) {
        return ResponseEntity.ok(cardService.deactivateCard(id));
    }
}
