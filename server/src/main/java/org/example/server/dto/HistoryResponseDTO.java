package org.example.server.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record HistoryResponseDTO(
        UUID id,
        UUID userId,
        UUID carId,
        LocalDateTime rentalTime,
        LocalDateTime returnTime
) {}