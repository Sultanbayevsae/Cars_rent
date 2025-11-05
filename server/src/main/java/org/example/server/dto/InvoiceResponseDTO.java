package org.example.server.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record InvoiceResponseDTO(
        UUID id,
        String invoiceNumber,
        BigDecimal amount,
        String currency,
        String status,
        Long rentalId,
        UUID userId,
        LocalDateTime createdAt,
        LocalDateTime paidAt
) {
}
