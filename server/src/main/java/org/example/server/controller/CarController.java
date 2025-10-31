package org.example.server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.server.dto.ApiResponse;
import org.example.server.dto.CarCreator;
import org.example.server.dto.CarUpdater;
import org.example.server.repository.CarRepository;
import org.example.server.service.CarService;
import org.example.server.utill.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Car Controller", description = "Car CRUD operations")
@RestController
@RequestMapping(AppConstants.BASE_URL + "/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @Operation(
            description = "Create a new Car",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "201",
                            description = "Car created!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "500",
                            description = "HTTP Status Internal Server Error"
                    )
            }
    )

    @PostMapping("/create")
    public ResponseEntity<ApiResponse>create(@RequestBody @Valid CarCreator creator){
        ApiResponse response = carService.createCar(creator);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            description = "Update an existing Car",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Car updated!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "Car not found!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "500",
                            description = "HTTP Status Internal Server Error"
                    )
            }
    )

    @PutMapping("/update")
    public ResponseEntity<ApiResponse>update(@RequestBody @Valid CarUpdater updater){
        ApiResponse response = carService.updateCar(updater);
        return ResponseEntity.ok(response);
    }

    @Operation(
            description = "Delete an existing Car",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Car deleted!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "Car not found!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "500",
                            description = "HTTP Status Internal Server Error"
                    )
            }
    )

    @DeleteMapping("/delete/{carId}")
    public ResponseEntity<ApiResponse>delete(@PathVariable UUID carId){
        ApiResponse response = carService.deleteCar(carId);
        if (response.getSuccess()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
        @Operation(
                description = "Get a Car by ID",
                responses = {
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(
                                responseCode = "200",
                                description = "Car found!"
                        ),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(
                                responseCode = "404",
                                description = "Car not found!"
                        ),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(
                                responseCode = "500",
                                description = "HTTP Status Internal Server Error"
                        )
                }
        )
        @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse>findById(@PathVariable UUID id) {
            ApiResponse response = carService.findCarById(id);
            if (response.getSuccess()) {
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        @Operation(
                description = "Get all Cars",
                responses = {
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(
                                responseCode = "200",
                                description = "Cars found!"
                        ),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(
                                responseCode = "404",
                                description = "Cars not found!"
                        ),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(
                                responseCode = "500",
                                description = "HTTP Status Internal Server Error"
                        )
                }
        )
        @GetMapping("/get-all")
    public ResponseEntity<ApiResponse> findAll() {
            ApiResponse response = carService.findAllCars();
            if (response.getSuccess()) {
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
}

