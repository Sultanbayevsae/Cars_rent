package org.example.server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UserPhotoCreator (
        @NotNull(message = "User ID bo‘sh bo‘lmasligi kerak")
        UUID userId,

        @NotBlank(message = "Content type bo‘sh bo‘lmasligi kerak")
        String contentType,

        @NotNull(message = "Photo bytes bo‘sh bo‘lmasligi kerak")
        Byte[] bytes
){
}
