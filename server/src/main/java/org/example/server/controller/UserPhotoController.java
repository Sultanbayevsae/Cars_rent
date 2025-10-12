package org.example.server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.server.dto.ApiResponse;
import org.example.server.exception.ErrorDTO;
import org.example.server.service.UserPhotoService;
import org.example.server.utill.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping(UserPhotoController.BASE_URL)
@Tag(
        name = "UserPhotoController",
        description = "Bu yerda foydalanuvchilarning rasmlari uchun CRUD API lar mavjud"
)
public class UserPhotoController {

    public static final String BASE_URL = AppConstants.BASE_URL + "/user-photo";

    private final UserPhotoService userPhotoService;

    public UserPhotoController(UserPhotoService userPhotoService) {
        this.userPhotoService = userPhotoService;
    }

    @Operation(
            description = "Yangi foydalanuvchi rasmi yuklash",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "201",
                            description = "UserPhoto yuklandi!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "417",
                            description = "Rasm yuklashda xatolik❌",
                            content = @Content(schema = @Schema(implementation = ApiResponse.class))
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "500",
                            description = "HTTP Status Internal Server Error",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))
                    )
            }
    )
//    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @PostMapping(value = "/upload/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> uploadPhoto(
            @PathVariable UUID userId,
            @RequestParam("file") MultipartFile file
    ) {
        ApiResponse response = userPhotoService.uploadPhoto(userId, file);
        return response.getSuccess()
                ? ResponseEntity.status(HttpStatus.CREATED).body(response)
                : ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
    }

    @Operation(
            description = "Foydalanuvchi rasmini ID orqali olish",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "UserPhoto topildi!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "UserPhoto topilmadi❌",
                            content = @Content(schema = @Schema(implementation = ApiResponse.class))
                    )
            }
    )
    @GetMapping(value = "/get/{userId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Byte[]> getPhoto(@PathVariable UUID userId) {
        Byte[] photoBytes = userPhotoService.getPhoto(userId);
        return ResponseEntity.ok(photoBytes);
    }

    @Operation(
            description = "Foydalanuvchi rasmini o‘chirish",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "204",
                            description = "UserPhoto o‘chirildi!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "UserPhoto topilmadi❌",
                            content = @Content(schema = @Schema(implementation = ApiResponse.class))
                    )
            }
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deletePhoto(@PathVariable UUID userId) {
        ApiResponse response = userPhotoService.deletePhoto(userId);
        return response.getSuccess()
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(response)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
