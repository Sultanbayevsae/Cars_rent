package org.example.server.dto;

import org.example.server.entity.PaymentMethod;
import org.example.server.entity.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentResponseDTO(
        Long id,
        PaymentMethod method,
        BigDecimal amount,
        PaymentStatus status,
        String transactionId,
        LocalDateTime paymentDate,
        Long rentalId
) {
}
