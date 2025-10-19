package org.example.server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UserPhotoCreator(
        @NotNull UUID userId,
        @NotBlank String contentType,
        byte[] bytes
) {}
