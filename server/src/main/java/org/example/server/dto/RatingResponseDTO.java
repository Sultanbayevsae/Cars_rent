package org.example.server.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record RatingResponseDTO(
        UUID id,
        Integer rating,
        UUID carId,
        UUID commentId,
        UUID userId,
        String username,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
