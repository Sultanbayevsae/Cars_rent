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
            Byte[] wrappedBytes = new Byte[fileBytes.length];
            for (int i = 0; i < fileBytes.length; i++) {
                wrappedBytes[i] = fileBytes[i];
            }

            UserPhotoCreator creator = new UserPhotoCreator(
                    userId,
                    file.getContentType(),
                    wrappedBytes
            );

            UserPhoto photo = userPhotoMapper.toEntity(creator, userRepository);

            userPhotoRepository.save(photo);

            return new ApiResponse(true, "UserPhoto uploaded successfully ✅");
        } catch (IOException e) {
            return new ApiResponse(false, "Error reading file ❌");
        }
    }

    @Override
    public Byte[] getPhoto(UUID userId) {
        Optional<UserPhoto> photoOpt = userPhotoRepository.findByUserId(userId);
        if (photoOpt.isPresent()) {
            Byte[] wrappedBytes = photoOpt.get().getBytes();
            Byte[] primitiveBytes = new Byte[wrappedBytes.length];
            for (int i = 0; i < wrappedBytes.length; i++) {
                primitiveBytes[i] = wrappedBytes[i];
            }
            return primitiveBytes;
        }
        return new Byte[0];
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

    @Override
    @Transactional
    public ApiResponse updatePhoto(UUID userId, MultipartFile file) {
        deletePhoto(userId);
        return uploadPhoto(userId, file);
    }
}
