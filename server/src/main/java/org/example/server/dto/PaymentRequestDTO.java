package org.example.server.dto;

import jakarta.validation.constraints.NotBlank;
import org.example.server.entity.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequestDTO(
        @NotBlank(message = "Rental ID must be provided")
        Long rentalId,
        @NotBlank(message = "Payment method must be provided")
        PaymentMethod method,
        @NotBlank(message = "Payment amount must be provided")
        BigDecimal amount,
        @NotBlank(message = "Transaction ID must be provided")
        String transactionId
) {
}
