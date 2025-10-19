package org.example.server.dto;

import java.util.UUID;

public record LIkedCarsCreator(
        UUID userId,
        UUID carId
) {
}
