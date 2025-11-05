package org.example.server.service.impl;

import org.example.server.dto.ApiResponse;
import org.example.server.dto.UserPhotoCreator;
import org.example.server.entity.UserPhoto;
import org.example.server.mapper.UserPhotoMapper;
import org.example.server.repository.UserPhotoRepository;
import org.example.server.repository.UserRepository;
import org.example.server.service.UserPhotoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserPhotoServiceImpl implements UserPhotoService {

    private final UserRepository userRepository;
    private final UserPhotoRepository userPhotoRepository;
    private final UserPhotoMapper userPhotoMapper;

    public UserPhotoServiceImpl(UserRepository userRepository,
                                UserPhotoRepository userPhotoRepository,
                                UserPhotoMapper userPhotoMapper) {
        this.userRepository = userRepository;
        this.userPhotoRepository = userPhotoRepository;
        this.userPhotoMapper = userPhotoMapper;
    }

    @Override
    @Transactional
    public ApiResponse uploadPhoto(UUID userId, MultipartFile file) {
        try {
            if (userRepository.findById(userId).isEmpty()) {
                return new ApiResponse(false, "User not found by id: " + userId);
            }

            userPhotoRepository.findByUserId(userId)
                    .ifPresent(userPhotoRepository::delete);
            byte[] fileBytes = file.getBytes();

            UserPhoto photo = new UserPhoto();
            photo.setBytes(fileBytes);
            photo.setContentType(file.getContentType());
            photo.setUser(userRepository.findById(userId).get());

            userPhotoRepository.save(photo);

            return new ApiResponse(true, "UserPhoto uploaded successfully ✅");
        } catch (IOException e) {
            return new ApiResponse(false, "Error reading file ❌");
        }
    }

    @Override
    public byte[] getPhoto(UUID userId) {
        Optional<UserPhoto> photoOpt = userPhotoRepository.findByUserId(userId);
        return photoOpt.map(UserPhoto::getBytes).orElse(new byte[0]);
    }

    @Override
    @Transactional
    public ApiResponse deletePhoto(UUID userId) {
        Optional<UserPhoto> photoOpt = userPhotoRepository.findByUserId(userId);
        if (photoOpt.isPresent()) {
            userPhotoRepository.delete(photoOpt.get());
            return new ApiResponse(true, "UserPhoto deleted successfully ✅");
        }
        return new ApiResponse(false, "UserPhoto not found by userId: " + userId);
    }

    @Transactional
    @Override
    public ApiResponse updatePhoto(UUID userId, MultipartFile file) {
        deletePhoto(userId);
        return uploadPhoto(userId, file);
    }
}
