package org.example.server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.server.dto.NotificationRequestDTO;
import org.example.server.dto.NotificationResponseDTO;
import org.example.server.service.NotificationService;
import org.example.server.utill.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(AppConstants.BASE_URL + "/notifications")
@Tag(
        name = "Notification Controller",
        description = "REST APIs for Notification management"
)
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    @Operation(summary = "Create notification for specific user")
    public ResponseEntity<NotificationResponseDTO> create(@RequestBody NotificationRequestDTO dto){
        return new ResponseEntity<>(notificationService.createNotification(dto), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get all notifications by user ID")
    public ResponseEntity<List<NotificationResponseDTO>> getUserNotifications(@PathVariable UUID userId){
        return ResponseEntity.ok(notificationService.getUserNotifications(userId));
    }
}
