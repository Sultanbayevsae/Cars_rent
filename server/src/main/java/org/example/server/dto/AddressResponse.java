package org.example.server.dto;

public record AddressResponse(
        String cityOrTown,
        String details
) {}