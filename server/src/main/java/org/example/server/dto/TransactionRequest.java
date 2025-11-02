package org.example.server.dto;

import jakarta.validation.constraints.NotBlank;
import org.example.server.entity.TransactionType;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionRequest (
        TransactionType type,
        @NotBlank(message = "Transaction amount must be provided")
        BigDecimal amount,
        String description,
        @NotBlank(message = "Payment ID must be provided")
        Long paymentId,
        @NotBlank(message = "Card ID must be provided")
        UUID cardId
) {
}
