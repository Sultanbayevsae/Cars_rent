package org.example.server.dto;

import java.util.UUID;

public record RatingCreateDTO(
        Integer rating,
        UUID carId,
        UUID commentId,
        UUID userId
) {
}
