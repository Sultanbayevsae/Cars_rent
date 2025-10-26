package org.example.server.mapper;

import org.example.server.dto.UserPhotoCreator;
import org.example.server.dto.UserPhotoResponse;
import org.example.server.entity.User;
import org.example.server.entity.UserPhoto;
import org.example.server.repository.UserRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserPhotoMapper {
    UserPhoto toEntity(UserPhotoCreator creator, @Context UserRepository userRepository);

    UserPhotoResponse toDto(UserPhoto entity);

    @Named("mapUser")
    default User toUser(UUID userId, @Context UserRepository userRepository) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found by id: " + userId));
    }
}
