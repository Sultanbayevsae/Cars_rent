package org.example.server.mapper;

import org.example.server.dto.HistoryRequestDTO;
import org.example.server.dto.HistoryResponseDTO;
import org.example.server.entity.History;
import org.example.server.entity.Car;
import org.example.server.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HistoryMapper {

    @Mapping(source = "user", target = "user")
    @Mapping(source = "car", target = "car")
    History toEntity(HistoryRequestDTO dto, User user, Car car);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "car.id", target = "carId")
    HistoryResponseDTO toResponse(History history);
}
