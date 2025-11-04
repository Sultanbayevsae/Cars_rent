package org.example.server.service;

import org.example.server.dto.ApiResponse;
import org.example.server.dto.RatingCreateDTO;
import org.example.server.dto.RatingUpdateDTO;



import java.util.UUID;


public interface RatingService {
    ApiResponse createRating(RatingCreateDTO ratingCreateDTO);
    ApiResponse deleteRating(UUID rating_id);
    ApiResponse updateRating(UUID rating_id, RatingUpdateDTO ratingUpdateDTO);
    ApiResponse getByIdRating(UUID rating_id);
    ApiResponse getByCarId(UUID car_id);
    ApiResponse getByCommentsId(UUID comments_id);
    ApiResponse getAverageRatingByCarId(UUID car_id);
    ApiResponse getAverageRatingByCommentsId(UUID comments_id);
}
