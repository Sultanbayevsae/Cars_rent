package org.example.server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.server.dto.ApiResponse;
import org.example.server.dto.LikedCarsCreator;
import org.example.server.exception.ErrorDTO;
import org.example.server.repository.LikedCarRepository;
import org.example.server.service.LikedCarsService;
import org.example.server.utill.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Tag(name = "Liked Cars Controller", description = "LikedCar entitysi bilan ishlash uchun CRUD API'lar")
@RestController
@RequestMapping(AppConstants.BASE_URL + "liked-cars")
public class LikedCarsController {

    private final LikedCarsService likedCarsService;
    private final LikedCarRepository likedCarRepository;


    public LikedCarsController(LikedCarsService likedCarsService, LikedCarRepository likedCarRepository) {
        this.likedCarsService = likedCarsService;
        this.likedCarRepository = likedCarRepository;
    }

    @Operation(
            description = "For Liking cars",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "201",
                            description = "Car liked!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "500",
                            description = "HTTP Status Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorDTO.class)
                            )
                    )
            }
    )
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid LikedCarsCreator likedCarCreator) {
        ApiResponse response = likedCarsService.likedCar(likedCarCreator);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            description = "Remove like from cars",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "204",
                            description = "Like removed!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "not liked yet!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "500",
                            description = "HTTP Status Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorDTO.class)
                            )
                    )
            }
    )
    @DeleteMapping("/delete/{likedCarId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable UUID likedCarId) {
        ApiResponse response = likedCarsService.deleteLikedCar(likedCarId);

        if(response.getSuccess()){
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @Operation(
            description = "All liked cars list",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Liked cars found!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "Liked cars not found!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "500",
                            description = "HTTP Status Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorDTO.class)
                            )
                    )
            }
    )
    @GetMapping("/get-all/{userId}")
    public ResponseEntity<ApiResponse>findAll(@PathVariable UUID userId) {
        ApiResponse response = likedCarsService.findAllLikedCars(userId);
        if(response.getSuccess()){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @Operation(
            description = "For removing all liked cars",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "All Liked Cars removed!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "Liked Cars not found!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "500",
                            description = "HTTP Status Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorDTO.class)
                            )
                    )
            }
    )
    @GetMapping("/remove-all/{userId}")
    public ResponseEntity<ApiResponse>removeAll(@PathVariable UUID userId) {
        ApiResponse response = likedCarsService.clearAllLikedCars(userId);
        if(response.getSuccess()){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
