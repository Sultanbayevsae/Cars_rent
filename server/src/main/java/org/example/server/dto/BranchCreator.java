package org.example.server.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record BranchCreator(
        @NotBlank(message = "Branch name must not be blank")
        String name,
        @Valid
        AddressCreator address
) {
}
