package org.example.server.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record NotificationRequestDTO(
        @NotNull(message = "User ID is required")
        UUID userId,

        @NotNull(message = "Car ID is required")
        UUID carId,

        @NotNull(message = "Transaction ID is required")
        UUID transactionId,

        String title,
        String message
) {}
