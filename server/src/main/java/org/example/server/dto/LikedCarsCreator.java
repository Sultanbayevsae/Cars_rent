package org.example.server.dto;

import java.util.UUID;

public record LikedCarsCreator(
        UUID userId,
        UUID carId
) {
}
