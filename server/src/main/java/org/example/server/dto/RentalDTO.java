package org.example.server.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record RentalDTO(

        Long id,

        @NotBlank(message = "User ID must not be blank")
        UUID userId,

        @NotBlank(message = "Car ID must not be blank")
        UUID carId,


        LocalDateTime startDate,
        LocalDateTime endDate,
        BigDecimal totalPrice,
        String rentalStatus
) {
}