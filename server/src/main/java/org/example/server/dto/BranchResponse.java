package org.example.server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record BranchResponse(
        @JsonProperty("branch_id")
        @NotNull(message = "Branch ID must be provided")
        UUID id,
        @JsonProperty("branch_name")
        String name,
        @JsonProperty("branch_address")
        String cityOrtown,
        @JsonProperty("branch_address_detail")
        String details
) {
}
