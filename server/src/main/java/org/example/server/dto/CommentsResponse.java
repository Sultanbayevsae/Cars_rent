package org.example.server.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CommentsResponse(
        UUID id, String comment, boolean isReviewer, UUID userId, UUID carId
) {
}
