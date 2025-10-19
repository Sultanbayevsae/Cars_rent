package org.example.server.mapper;

import org.example.server.dto.RegisterDto;
import org.example.server.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "history", ignore = true)
    @Mapping(target = "likedCars", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "verifyToken", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    User toEntity(RegisterDto dto);
}
