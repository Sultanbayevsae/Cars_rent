package org.example.server.dto;

import jakarta.validation.constraints.*;
import org.example.server.validation.branch.ValidBranchId;

import java.util.UUID;

public record CarUpdater(
        @NotNull(message = "Car ID is strictly required")
        UUID id,

        @Size(max = 100, message = "Car name must be less than 100 characters")
        String name,


        @Size(max = 50, message = "Car number must be less than 50 characters")
        String carNumber,

        @Positive(message = "Gas used must be positive")
        Integer gasUsed,

        @Positive(message = "Price per day must be positive")
        Integer pricePerDay,

        @NotNull(message = "Please specify if the car is automatic or manual")
        Boolean autoOrManual,
        @NotNull(message = "Please specify if the car is available")
        Boolean isAvailable,

        @Min(value = 1, message = "A car must have at least 1 seat")
        Integer seats,

        @ValidBranchId
        @NotNull(message = "Branch ID cannot be null")
        UUID branchId) {
}
