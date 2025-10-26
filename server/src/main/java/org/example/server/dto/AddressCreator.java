package org.example.server.dto;

import jakarta.validation.constraints.NotBlank;

public record AddressCreator(
        @NotBlank(message = "City or town must be given")
        String cityOrTown,
        @NotBlank(message = "Give a little bit info")
        String details
) {}