package org.example.server.service.impl;

import org.example.server.dto.ApiResponse;
import org.example.server.dto.CarCreator;
import org.example.server.dto.CarUpdater;
import org.example.server.entity.Car;
import org.example.server.mapper.CarMapper;
import org.example.server.repository.CarRepository;
import org.example.server.service.CarService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CarImplService implements CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarImplService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    @Override
    public ApiResponse createCar(CarCreator creator) {
        Car entity = carMapper.toEntity(creator, carRepository);
        carRepository.save(entity);
        return new ApiResponse(true, "Car created!");
    }

    @Override
    public ApiResponse updateCar(CarUpdater updater) {
        return null;
    }

    @Override
    public ApiResponse findCarById(UUID carId) {
        return null;
    }

    @Override
    public ApiResponse findAllCars() {
        return null;
    }

    @Override
    public ApiResponse deleteCar(UUID carId) {
        return null;
    }
}
