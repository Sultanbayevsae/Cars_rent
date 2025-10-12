package org.example.server.service;

import jakarta.validation.Valid;
import org.example.server.dto.ApiResponse;
import org.example.server.dto.CarCreator;
import org.example.server.dto.CarUpdater;

import java.util.UUID;

public interface CarService {
    ApiResponse createCar(@Valid CarCreator creator);
    ApiResponse updateCar(@Valid CarUpdater updater);
    ApiResponse deleteCar(UUID carId);
    ApiResponse findCarById(UUID carId);
    ApiResponse findAllCars();
}
