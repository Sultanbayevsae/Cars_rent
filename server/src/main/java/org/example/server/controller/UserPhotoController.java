package org.example.server.controller;

import org.example.server.dto.ApiResponse;
import org.example.server.service.UserPhotoService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user-photo")
public class UserPhotoController {
    private final UserPhotoService userPhotoService;

    public UserPhotoController(UserPhotoService userPhotoService) {
        this.userPhotoService = userPhotoService;
    }

    @PostMapping(value = "/upload/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> uploadPhoto(
            @PathVariable UUID userId,
            @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(userPhotoService.uploadPhoto(userId, file));
    }

    @GetMapping(value = "/get/{userId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getPhoto(@PathVariable UUID userId) {
        byte[] photoBytes = userPhotoService.getPhoto(userId);
        return ResponseEntity.ok(photoBytes);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deletePhoto(@PathVariable UUID userId) {
        return ResponseEntity.ok(userPhotoService.deletePhoto(userId));
    }
}
