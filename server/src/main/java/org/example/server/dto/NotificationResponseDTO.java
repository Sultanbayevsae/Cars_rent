package org.example.server.dto;

import java.util.UUID;

public record NotificationResponseDTO(
        UUID id,
        UUID userId,
        UUID carId,
        UUID transactionId,
        String title,
        String message,
        boolean read
) {}
