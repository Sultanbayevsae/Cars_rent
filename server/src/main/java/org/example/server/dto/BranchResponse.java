package org.example.server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record BranchResponse(
        UUID id,
        String name,
        AddressResponse address
) {}
