package org.example.server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.server.dto.ApiResponse;
import org.example.server.service.UserPhotoService;
import org.example.server.utill.AppConstants;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping(AppConstants.BASE_URL + "/user-photo")
@Tag(
        name = "User Photo Controller",
        description = ""
)
public class UserPhotoController {
    private final UserPhotoService userPhotoService;

    public UserPhotoController(UserPhotoService userPhotoService) {
        this.userPhotoService = userPhotoService;
    }

    @PostMapping(value = "/upload/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload user photo by ID")
    public ResponseEntity<ApiResponse> uploadPhoto(
            @PathVariable UUID userId,
            @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(userPhotoService.uploadPhoto(userId, file));
    }

    @GetMapping(value = "/get/{userId}", produces = MediaType.IMAGE_JPEG_VALUE)
    @Operation(summary = "Get User photo by user ID")
    public ResponseEntity<byte[]> getPhoto(@PathVariable UUID userId) {
        byte[] photoBytes = userPhotoService.getPhoto(userId);
        return ResponseEntity.ok(photoBytes);
    }

    @PutMapping(value = "/{userId}", consumes =  MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Update existing user photo by User ID")
    public ResponseEntity<ApiResponse> updatePhoto(
            @PathVariable UUID userId,
            @RequestParam("file") MultipartFile file
    ){
        return ResponseEntity.ok(userPhotoService.updatePhoto(userId, file));
    }

    @DeleteMapping("/delete/{userId}")
    @Operation(summary = "Delete user photo by user ID")
    public ResponseEntity<ApiResponse> deletePhoto(@PathVariable UUID userId) {
        return ResponseEntity.ok(userPhotoService.deletePhoto(userId));
    }
}
