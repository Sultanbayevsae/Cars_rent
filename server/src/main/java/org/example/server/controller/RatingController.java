package org.example.server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.server.dto.ApiResponse;
import org.example.server.dto.RatingCreateDTO;
import org.example.server.dto.RatingUpdateDTO;
import org.example.server.service.RatingService;
import org.example.server.utill.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Rating Controller", description = "Rating related operations")
@RestController
@RequestMapping(AppConstants.BASE_URL + "/ratings")
public class RatingController {
    private final RatingService ratingService;
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @Operation(
            description = "Create a new Rating",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "201",
                            description = "Rating created!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "500",
                            description = "HTTP Status Internal Server Error"
                    )
            }
    )
    @PostMapping("/create")
    public ResponseEntity<ApiResponse>createRating(@RequestBody RatingCreateDTO ratingCreateDTO){
        ApiResponse response = ratingService.createRating(ratingCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @Operation(
            description = "Update an existing Rating",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Rating updated!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "Rating not found!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "500",
                            description = "HTTP Status Internal Server Error"
                    )
            }
    )
    @PutMapping("/get/{id}/update")
    public ResponseEntity<ApiResponse>updateRating(@RequestBody  @Valid RatingUpdateDTO ratingUpdateDTO, @PathVariable UUID id){
        ApiResponse response = ratingService.updateRating(id, ratingUpdateDTO);
        return ResponseEntity.ok(response);
    }


    @Operation(
            description = "Delete an existing Rating",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "204",
                            description = "Rating deleted!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "Rating not found!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "500",
                            description = "HTTP Status Internal Server Error"
                    )
            }
    )

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse>deleteRating(@PathVariable UUID id){
      ApiResponse response = ratingService.deleteRating(id);
      if(response.getSuccess()){
          return ResponseEntity.ok(response);
        }
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @Operation(
            description = "Get Rating by ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Rating found!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "Rating not found!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "500",
                            description = "HTTP Status Internal Server Error"
                    )
            }
    )
    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse>getRatingById(@PathVariable UUID id){
        ApiResponse response = ratingService.getByIdRating(id);
        if(response.getSuccess()){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @Operation(
            description = "Get Ratings by Car ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Ratings found!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "Ratings not found!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "500",
                            description = "HTTP Status Internal Server Error"
                    )
            }
    )
    @GetMapping("/car/{car_id}")
    public ResponseEntity<ApiResponse>getRatingsByCarId(@PathVariable UUID car_id){
        ApiResponse response = ratingService.getByCarId(car_id);
        if(response.getSuccess()){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @Operation(
            description = "Get Ratings by Comments ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Ratings found!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "Ratings not found!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "500",
                            description = "HTTP Status Internal Server Error"
                    )
            }
    )
    @GetMapping("/comments/{comments_id}")
    public ResponseEntity<ApiResponse>getRatingsByCommentsId(@PathVariable UUID comments_id){
        ApiResponse response = ratingService.getByCommentsId(comments_id);
        if(response.getSuccess()){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @Operation(
            description = "Get Average Rating by Car ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Average Rating found!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "Average Rating not found!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "500",
                            description = "HTTP Status Internal Server Error"
                    )
            }
    )
    @GetMapping("/car/{car_id}/average")
    public ResponseEntity<ApiResponse>getAverageRatingByCarId(@PathVariable UUID car_id){
        ApiResponse response = ratingService.getAverageRatingByCarId(car_id);
        if(response.getSuccess()){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @Operation(
            description = "Get Average Rating by Comments ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Average Rating found!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "Average Rating not found!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "500",
                            description = "HTTP Status Internal Server Error"
                    )
            }
    )
    @GetMapping("/comments/{comments_id}/average")
    public ResponseEntity<ApiResponse>getAverageRatingByCommentsId(@PathVariable UUID comments_id){
        ApiResponse response = ratingService.getAverageRatingByCommentsId(comments_id);
        if(response.getSuccess()){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


}
