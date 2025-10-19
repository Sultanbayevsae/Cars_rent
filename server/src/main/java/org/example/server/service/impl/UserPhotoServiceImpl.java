package org.example.server.service.impl;

import org.example.server.dto.ApiResponse;
import org.example.server.dto.UserPhotoCreator;
import org.example.server.entity.UserPhoto;
import org.example.server.mapper.UserPhotoMapper;
import org.example.server.repository.UserPhotoRepository;
import org.example.server.service.UserPhotoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class UserPhotoServiceImpl implements UserPhotoService {
    private final UserPhotoRepository userPhotoRepository;
    private final UserPhotoMapper userPhotoMapper;

    public UserPhotoServiceImpl(UserPhotoRepository userPhotoRepository, UserPhotoMapper userPhotoMapper) {
        this.userPhotoRepository = userPhotoRepository;
        this.userPhotoMapper = userPhotoMapper;
    }

    @Override
    @Transactional
    public ApiResponse uploadPhoto(UUID userId, MultipartFile file) {
        try {
            userPhotoRepository.findByUserId(userId)
                    .ifPresent(userPhotoRepository::delete);

            byte[] fileBytes = file.getBytes();
            UserPhotoCreator creator = new UserPhotoCreator(
                    userId,
                    file.getContentType(),
                    fileBytes
            );

            UserPhoto photo = userPhotoMapper.toEntity(creator);
            userPhotoRepository.save(photo);

            return new ApiResponse(true, "UserPhoto uploaded successfully ✅");
        } catch (IOException e) {
            return new ApiResponse(false, "Error reading file ❌");
        }
    }

    @Override
    public byte[] getPhoto(UUID userId) {
        return userPhotoRepository.findByUserId(userId)
                .map(UserPhoto::getBytes)
                .orElse(new byte[0]);
    }

    @Override
    @Transactional
    public ApiResponse deletePhoto(UUID userId) {
        userPhotoRepository.findByUserId(userId)
                .ifPresent(userPhotoRepository::delete);
        return new ApiResponse(true, "UserPhoto deleted successfully ✅");
    }
}
