package org.example.server.service;

import org.example.server.dto.NotificationRequestDTO;
import org.example.server.dto.NotificationResponseDTO;

import java.util.List;
import java.util.UUID;

public interface NotificationService {

    NotificationResponseDTO createNotification(NotificationRequestDTO dto);

    List<NotificationResponseDTO> getUserNotifications(UUID userId);
}
