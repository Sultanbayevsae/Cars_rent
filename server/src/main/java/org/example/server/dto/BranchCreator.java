package org.example.server.dto;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record BranchCreator(
        @NotBlank(message = "Branch name must not be blank")
        String name,
        @NotBlank(message = "Branch address must not be blank")
        UUID addressId
) {
}
