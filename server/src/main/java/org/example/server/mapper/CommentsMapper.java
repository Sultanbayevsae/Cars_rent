package org.example.server.mapper;


import org.example.server.dto.CommentUpdateDTO;
import org.example.server.dto.CommentsCreator;
import org.example.server.dto.CommentsResponse;
import org.example.server.entity.Comments;
import org.example.server.repository.CarRepository;
import org.example.server.repository.CommentsRepository;
import org.example.server.repository.UserRepository;
import org.mapstruct.*;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CommentsMapper {



//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "user", source = "creator.userId", qualifiedByName = "mapUser")
//    @Mapping(target = "car", source = "creator.carId", qualifiedByName = "mapCar")
//    @Mapping(target = "ratings", ignore = true)
    Comments toEntity(CommentsCreator creator, @Context UserRepository userRepository, @Context CarRepository carRepository);


    @Named("mapUser")
    default org.example.server.entity.User mapUser(java.util.UUID userId, @Context UserRepository repository) {
        return userId == null ? null : repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
    }

    @Named("mapCar")
    default org.example.server.entity.Car mapCar(java.util.UUID carId, @Context CarRepository repository) {
        return carId == null ? null : repository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found: " + carId));
    }

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    @Mapping(target = "user", source = "updater.user", qualifiedByName = "mapUserEntity")
//    @Mapping(target = "car", source = "updater.car", qualifiedByName = "mapCarEntity")
//    @Mapping(target = "ratings", ignore = true)
    void updateCommentFromDto(CommentUpdateDTO updater, @MappingTarget Comments comments, @Context UserRepository userRepository, @Context CarRepository carRepository);

    @Named("mapUserEntity")
    default org.example.server.entity.User mapUserEntity(org.example.server.entity.User user, @Context UserRepository repository) {
        if(user == null) return null;
        return repository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found: " + user.getId()));
    }
    @Named("mapCarEntity")
    default org.example.server.entity.Car mapCarEntity(org.example.server.entity.Car car, @Context CarRepository repository) {
        if(car == null) return null;
        return repository.findById(car.getId())
                .orElseThrow(() -> new RuntimeException("Car not found: " + car.getId()));
    }


    CommentsResponse toDto(Comments entity);

    List<CommentsResponse> toDtoList(List<Comments> entities);

}
