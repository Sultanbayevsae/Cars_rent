package org.example.server.service.impl;

import org.example.server.dto.ApiResponse;
import org.example.server.dto.CarCreator;
import org.example.server.dto.CarUpdater;
import org.example.server.entity.Car;
import org.example.server.mapper.CarMapper;
import org.example.server.repository.BranchRepository;
import org.example.server.repository.CarRepository;
import org.example.server.service.CarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarImplService implements CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final BranchRepository branchRepository;

    public CarImplService(CarRepository carRepository, CarMapper carMapper, BranchRepository branchRepository) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
        this.branchRepository = branchRepository;
    }

    @Override
    @Transactional
    public ApiResponse createCar(CarCreator creator) {
        Car entity = carMapper.toEntity(creator, branchRepository);
        carRepository.save(entity);
        return new ApiResponse(true, "Car created!");
    }

    @Override
    @Transactional
    public ApiResponse updateCar(CarUpdater updater) {
        Car entity = carRepository.findById(updater.id())
                .orElseThrow(() -> new RuntimeException("Car with id " + updater.id() + " not found!"));

        carMapper.updateCarFromDto(updater, entity, branchRepository);

        carRepository.save(entity);
        return new ApiResponse(true, "Car updated!");
    }

    @Override
    public ApiResponse findCarById(UUID carId) {
        Optional<Car> entity = carRepository.findById(carId);
        return entity.map(car -> new ApiResponse(true, "Car found!", car)).orElseGet(() -> new ApiResponse(false, "Car with id " + carId + " not found!"));
    }

    @Override
    public ApiResponse findAllCars() {
        List<Car> cars = carRepository.findAll();
        if(cars.isEmpty()) return new ApiResponse(false, "No cars found!");
        return new ApiResponse(true, "Cars found!", cars);
    }

    @Override
    @Transactional
    public ApiResponse deleteCar(UUID carId) {

        if(!carRepository.existsById(carId)) {
            return new ApiResponse(false, "Car with id " + carId + " not found!");
        }
        carRepository.deleteById(carId);
        return new ApiResponse(true, "Car deleted!");
    }
}
