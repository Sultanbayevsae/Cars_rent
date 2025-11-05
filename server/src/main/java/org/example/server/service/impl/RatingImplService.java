package org.example.server.service.impl;

import org.example.server.dto.ApiResponse;
import org.example.server.dto.RatingCreateDTO;
import org.example.server.dto.RatingUpdateDTO;
import org.example.server.entity.Comments;
import org.example.server.entity.Rating;
import org.example.server.repository.RatingRepository;
import org.example.server.repository.CarRepository;
import org.example.server.repository.CommentsRepository;
import org.example.server.repository.UserRepository;
import org.example.server.service.RatingService;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RatingImplService implements RatingService {
    private final RatingRepository ratingRepository;
    private final CarRepository carRepository;
    private final CommentsRepository commentsRepository;
    private final UserRepository userRepository;
    private final AuditorAware<UUID> auditorAware;


    public RatingImplService(RatingRepository ratingRepository, CarRepository carRepository, CommentsRepository commentsRepository, UserRepository userRepository, AuditorAware auditorAware) {
        this.ratingRepository = ratingRepository;
        this.carRepository = carRepository;
        this.commentsRepository = commentsRepository;
        this.userRepository = userRepository;
        this.auditorAware = auditorAware;
    }


    @Override
    @Transactional
    public ApiResponse createRating(RatingCreateDTO ratingCreateDTO) {
        UUID user = auditorAware.getCurrentAuditor()
                .orElseThrow(() -> new RuntimeException("User not found"));
        Rating rating = new Rating();

        rating.setRating(ratingCreateDTO.rating());
        rating.setUser(userRepository.findById(user).orElseThrow(() -> new RuntimeException("User not found")));

        if(ratingCreateDTO.carId() != null){
           if(ratingRepository.findByCar_IdAndCar_Id(user, ratingCreateDTO.carId())){
               return new ApiResponse(false, "You have already rated this car");
           }
           rating.setCar(carRepository.findById(ratingCreateDTO.carId())
                   .orElseThrow(() -> new RuntimeException("Car not found")));
        }

        if(ratingCreateDTO.commentId() != null){
            if(ratingRepository.existsByUser_IdAndComments_Id(user, ratingCreateDTO.commentId())){
                return new ApiResponse(false, "You have already rated this comment");
            }
            rating.setComments(commentsRepository.findById(ratingCreateDTO.commentId())
                    .orElseThrow(() -> new RuntimeException("Comment not found")));
        }
        ratingRepository.save(rating);
        return new ApiResponse(true, "Rating created!");
    }

    @Override
    @Transactional
    public ApiResponse deleteRating(UUID rating_id) {
        UUID user = auditorAware.getCurrentAuditor()
                .orElseThrow(() -> new RuntimeException("User not found"));
        Rating rating = ratingRepository.findById(rating_id)
                .orElseThrow(() -> new RuntimeException("Rating not found"));
        if(!rating.getUser().getId().equals(user)){
            return new ApiResponse(false, "You are not authorized to delete this rating");
        }
        ratingRepository.delete(rating);
        return new ApiResponse(true, "Rating deleted");
    }

    @Override
    @Transactional
    public ApiResponse updateRating(UUID rating_id, RatingUpdateDTO ratingUpdateDTO) {
        UUID user = auditorAware.getCurrentAuditor()
                .orElseThrow(() -> new RuntimeException("User not found"));
        Rating rating = ratingRepository.findById(rating_id)
                .orElseThrow(() -> new RuntimeException("Rating not found"));
        if(!rating.getUser().getId().equals(user)){
            return new ApiResponse(false, "You are not authorized to update this rating");
        }

            rating.setRating(ratingUpdateDTO.rating());
            ratingRepository.save(rating);
            return new ApiResponse(true, "Rating updated");

    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse getByIdRating(UUID rating_id) {
        Rating rating = ratingRepository.findById(rating_id)
                .orElseThrow(() -> new RuntimeException("Rating not found"));
        return new ApiResponse(true, "Rating found" , rating);
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse getByCarId(UUID car_id) {
        List<Rating>ratings = ratingRepository.findAllByCar_Id(car_id);
        if(ratings.isEmpty()){
            return new ApiResponse(false, "No ratings found for this car");
        }
        return new ApiResponse(true, "Rating found" , ratings.stream().collect(Collectors.toList()));
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse getByCommentsId(UUID comments_id) {
        List<Comments>comments = commentsRepository.findAllById(List.of(comments_id));
        if(comments.isEmpty()){
            return new ApiResponse(false, "No ratings found for this comment");
        }
        return new ApiResponse(true, "Rating found" , comments.stream().collect(Collectors.toList()));
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse getAverageRatingByCarId(UUID car_id) {
        Double average = ratingRepository.findAverageRatingByCarId(car_id);
        if(average == null){
            return new ApiResponse(false, "No ratings found for this car");
        }
        return new ApiResponse(true, "Average rating found" , average);
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse getAverageRatingByCommentsId(UUID comments_id) {
        Double average = ratingRepository.getAverageRatingByCommnentsId(comments_id);
        if(average == null){
            return new ApiResponse(false, "No ratings found for this comment");
        }
        return new ApiResponse(true, "Average rating found" , average);
    }
}
