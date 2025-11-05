package org.example.server.dto;

import java.util.UUID;

public record PaymentCardResponseDTO(
        UUID id,
        String cardNumberMasked,
        String cardHolderName,
        String expiryDate,
        boolean isActive,
        UUID userId
) {}
