package org.example.server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record UserPhotoResponse(
        @JsonProperty("photo_id")
        UUID id,

        @JsonProperty("content_type")
        String contentType,

        @JsonProperty("user_id")
        UUID userId
) {
}
