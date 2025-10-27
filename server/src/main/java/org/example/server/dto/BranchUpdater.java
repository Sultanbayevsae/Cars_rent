package org.example.server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record BranchUpdater(
        @NotNull(message = "Branch ID must be provided")
        UUID id,
        @NotBlank(message = "Branch name must not be blank")
        String name,
        @NotBlank(message = "Branch address must not be blank")
        UUID addressId
) {
}
