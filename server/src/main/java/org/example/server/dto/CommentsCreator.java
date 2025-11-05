package org.example.server.dto;

import java.util.UUID;

public record CommentsCreator(
        UUID userId,
        UUID carId,
        String text,
        boolean isReviewer) {
}
