package org.example.server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PaymentCardRequestDTO(
        @NotBlank(message = "Card number is required")
        String cardNumberMasked,

        @NotBlank(message = "Card holder name is required")
        String cardHolderName,

        @NotBlank(message = "Expiry date is required")
        String expiryDate,

        @NotNull(message = "User ID is required")
        UUID userId
) {
}
