package org.example.server.service.impl;

import org.example.server.dto.ApiResponse;
import org.example.server.dto.LikedCarsCreator;
import org.example.server.entity.Car;
import org.example.server.entity.LikedCars;
import org.example.server.entity.User;
import org.example.server.repository.CarRepository;
import org.example.server.repository.LikedCarRepository;
import org.example.server.repository.UserRepository;
import org.example.server.service.LikedCarsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LikedCarServiceImpl implements LikedCarsService {

    private final LikedCarRepository likedCarRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    public LikedCarServiceImpl(LikedCarRepository likedCarRepository, UserRepository userRepository, CarRepository carRepository) {
        this.likedCarRepository = likedCarRepository;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }

    @Override
    @Transactional
    public ApiResponse likedCar(LikedCarsCreator creator) {
        Optional<User>userOpt = userRepository.findById(creator.userId());
        if (userOpt.isEmpty()){
            return new ApiResponse(false, "User not found");
        }
        Optional<Car>carOpt = carRepository.findById(creator.carId());
        if (carOpt.isEmpty()){
            return new ApiResponse(false, "Car not found");
        }

        boolean exists = likedCarRepository.existsByUserIdAndCarId(creator.userId(), creator.carId());
        if (exists){
            return new ApiResponse(false, "Car already liked by user");
        }

        LikedCars likedCars = new LikedCars();
        likedCars.setUser(userOpt.get());
        likedCars.setCar(carOpt.get());
        likedCarRepository.save(likedCars);

        return new ApiResponse(true, "Liked car successfully");
    }

    @Override
    public ApiResponse findAllLikedCars(UUID userId) {
        Optional<User>userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()){
            return new ApiResponse(false, "User not found");
        }
        List<LikedCars>likedCarsList = likedCarRepository.findAllByUserId(userId);
        if (likedCarsList.isEmpty()){
            return new ApiResponse(false, "No liked cars found for this user");
        }
        return new ApiResponse(true, "Liked cars retrieved successfully", likedCarsList);
    }

    @Override
    public ApiResponse deleteLikedCar(UUID id) {
        Optional<LikedCars>likedCarsOpt = likedCarRepository.findById(id);
        if (likedCarsOpt.isEmpty()){
            return new ApiResponse(false, "Liked car not found");
        }
        likedCarRepository.deleteById(id);
        return new ApiResponse(true, "Liked car deleted successfully");
    }


    @Override
    @Transactional
    public ApiResponse clearAllLikedCars(UUID userId) {
        if(!userRepository.existsById(userId)){
            return new ApiResponse(false, "User not found");
        }
        likedCarRepository.deleteAllByUserId(userId);
        return new ApiResponse(true, "Liked cars deleted successfully");
    }
}
