package org.example.server.service;

import jakarta.validation.Valid;
import org.example.server.dto.ApiResponse;
import org.example.server.dto.LikedCarsCreator;

import java.util.UUID;

public interface LikedCarsService {
    ApiResponse likedCar(LikedCarsCreator creator);
    ApiResponse findAllLikedCars(UUID userId);
    ApiResponse deleteLikedCar(@Valid UUID id);
    ApiResponse clearAllLikedCars(UUID userId);
}
