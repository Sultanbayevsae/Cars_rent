package org.example.server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record BranchCreator(
        @NotBlank(message = "Branch name must not be blank")
        String name,
        @NotBlank(message = "Branch address must not be blank")
        String CityOrTown,
        @NotBlank(message = "Branch detail address must not be blank")
        String detailAddress
) {
}
