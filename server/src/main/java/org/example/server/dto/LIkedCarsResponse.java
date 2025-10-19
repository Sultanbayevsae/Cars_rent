package org.example.server.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record LIkedCarsResponse(
        @NotNull(message = "ID must not be null")
        UUID id,
        @NotNull(message = "User ID must not be null")
        UUID userId,
        @NotNull(message = "Car ID must not be null")
        UUID carId
      ) {
}
