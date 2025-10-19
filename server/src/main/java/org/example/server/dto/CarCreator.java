package org.example.server.dto;

import jakarta.validation.constraints.*;
import org.example.server.validation.branch.ValidBranchId;

import java.util.UUID;

public record CarCreator(

        @NotBlank(message = "Car name cannot be empty")
        @Size(max = 100, message = "Car name must be less than 100 characters")
        String name,

        @NotBlank(message = "Car number cannot be empty")
        @Size(max = 50, message = "Car number must be less than 50 characters")
        String carNumber,

        @NotNull(message = "Gas used must be provided")
        @Positive(message = "Gas used must be positive")
        Integer gasUsed,

        @NotNull(message = "Price per day must be provided")
        @Positive(message = "Price per day must be positive")
        Integer pricePerDay,

        @NotNull(message = "autoOrManual field cannot be null")
        Boolean autoOrManual,

        @NotNull(message = "Availability must be specified")
        Boolean isAvailable,

        @NotNull(message = "Seats number must be provided")
        @Min(value = 1, message = "A car must have at least 1 seat")
        Integer seats,

        @ValidBranchId
        @NotNull(message = "Branch ID must be provided")
        UUID branchId
) {
}
