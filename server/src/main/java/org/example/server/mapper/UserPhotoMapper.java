package org.example.server.mapper;

import org.example.server.dto.UserPhotoCreator;
import org.example.server.entity.User;
import org.example.server.entity.UserPhoto;
import org.example.server.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserPhotoMapper {
    @Mapping(target = "user", source = "userId", qualifiedByName = "mapUser")
    UserPhoto toEntity(UserPhotoCreator creator);

    @Named("mapUser")
    default User mapUser(UUID userId) {
        return new User();
    }
}
