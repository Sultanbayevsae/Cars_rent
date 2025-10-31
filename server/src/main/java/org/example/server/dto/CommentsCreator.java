package org.example.server.dto;

import java.util.UUID;

public record CommentsCreator(
        UUID carId,
        String text,
        boolean isReviewer) {
}
