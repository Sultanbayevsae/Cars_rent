package org.example.server.dto;

import java.util.UUID;

public record BranchResponse(
        UUID id,
        String name,
        AddressResponse address
) {}
