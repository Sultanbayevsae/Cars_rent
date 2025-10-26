//package org.example.server.mapper;
//
//import org.example.server.dto.LIkedCarsCreator;
//import org.example.server.dto.LIkedCarsResponse;
//import org.example.server.entity.Car;
//import org.example.server.entity.User;
//import org.example.server.repository.CarRepository;
//import org.example.server.repository.UserRepository;
//import org.mapstruct.Context;
//import org.mapstruct.Mapping;
//import org.mapstruct.Named;
//
//import java.util.UUID;
//
//public interface LikedCarsMapper {
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "user", source = "creator.userId", qualifiedByName = "mapUser")
//    @Mapping(target = "car", source = "creator.carId", qualifiedByName = "mapCar")
//    LikedCars toEntity(LIkedCarsCreator creator, @Context UserRepository userRepo, @Context CarRepository carRepo);
//
//    @Mapping(target = "userId", source = "user.id")
//    @Mapping(target = "firstName", source = "user.firstName")
//    @Mapping(target = "lastName", source = "user.lastName")
//    @Mapping(target = "carId", source = "car.id")
//    @Mapping(target = "carName", source = "car.name")
//    LIkedCarsResponse toResponse(LikedCars likedCar);
//
//    @Named("mapUser")
//    default User mapUser(UUID id, @Context UserRepository repo) {
//        return repo.findById(id)
//                .orElseThrow(() -> new RuntimeException("User not found: " + id));
//    }
//
//    @Named("mapCar")
//    default Car mapCar(UUID id, @Context CarRepository repo) {
//        return repo.findById(id)
//                .orElseThrow(() -> new RuntimeException("Car not found: " + id));
//    }
//}
