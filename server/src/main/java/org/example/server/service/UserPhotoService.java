package org.example.server.service;

import org.example.server.dto.ApiResponse;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface UserPhotoService {
    ApiResponse uploadPhoto(UUID userId, MultipartFile file);
    Byte[] getPhoto(UUID userId);
    ApiResponse deletePhoto(UUID userId);

    @Transactional
    ApiResponse updatePhoto(UUID userId, MultipartFile file);
}
