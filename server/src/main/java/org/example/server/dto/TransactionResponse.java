package org.example.server.dto;

import org.example.server.entity.TransactionStatus;
import org.example.server.entity.TransactionType;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionResponse(
        UUID id,
        TransactionType type,
        TransactionStatus status,
        BigDecimal amount,
        String description,
        String bankTransactionId,
        Long paymentId,
        UUID cardId,
        UUID createdBy,
        String createdAt
) {
}
