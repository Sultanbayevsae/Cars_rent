package org.example.server.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

public record HistoryRequestDTO(
        @NotNull(message = "User ID is required")
        UUID userId,

        @NotNull(message = "Car ID is required")
        UUID carId,

        @NotNull(message = "Rental time is required")
        LocalDateTime rentalTime,

        LocalDateTime returnTime
) {}
