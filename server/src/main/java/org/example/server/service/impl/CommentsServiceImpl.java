package org.example.server.service.impl;


import org.example.server.dto.ApiResponse;
import org.example.server.dto.CommentUpdateDTO;
import org.example.server.dto.CommentsCreator;
import org.example.server.entity.Car;
import org.example.server.entity.Comments;
import org.example.server.mapper.CarMapper;
import org.example.server.mapper.CommentsMapper;
import org.example.server.repository.CarRepository;
import org.example.server.repository.CommentsRepository;
import org.example.server.repository.UserRepository;
import org.example.server.service.CommentsService;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommentsServiceImpl implements CommentsService {
    private final CommentsRepository commentsRepository;
    private final CommentsMapper commentsMapper;
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final AuditorAware<UUID> auditorAware;


    public CommentsServiceImpl(CommentsRepository commentsRepository, CommentsMapper commentsMapper, UserRepository userRepository, CarRepository carRepository, AuditorAware<UUID> auditorAware) {
        this.commentsRepository = commentsRepository;
        this.commentsMapper = commentsMapper;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.auditorAware = auditorAware;
    }


    @Override
    @Transactional
    public ApiResponse createComment(CommentsCreator dto) {
        Comments entity = commentsMapper.toEntity(dto, userRepository, carRepository);
        commentsRepository.save(entity);
        return new ApiResponse(true, "Comment created!");
    }

    @Override
    public ApiResponse getCommentById(UUID commentId) {
        Optional<Comments> entity = commentsRepository.findById(commentId);

        if (entity.isPresent()) {
            return new ApiResponse(true, "Comment found!", entity.get());
        } else {
            return new ApiResponse(false, "Comment with id " + commentId + " not found!");
        }
    }

    @Override
    public ApiResponse getAllCommentsByCarId(UUID carId) {
        Optional<Car> car = carRepository.findById(carId);
        if (car.isEmpty()) {
            return new ApiResponse(false, "Car with id " + carId + " not found!");
        }
        List<Comments> comments = car.get().getComments();

        if (comments.isEmpty()) {
            return new ApiResponse(false, "No comments found for car with id " + carId + "!");
        } else {
            return new ApiResponse(true, "Comments found!", comments);
        }

    }

    @Override
    public ApiResponse updateComment(UUID commentId, CommentUpdateDTO dto) {
        Comments comments = commentsRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment with id " + commentId + " not found!"));

        UUID currentUserId = auditorAware.getCurrentAuditor()
                .orElseThrow(() -> new RuntimeException("Current user not found!"));

        if(!comments.getUser().getId().equals(currentUserId)) {
            throw new RuntimeException("You can only edit your own comments!");
        }

       if(dto.text() == null || dto.text().isBlank()){
           return new ApiResponse(false, "Text field is required!");
       }

       comments.setComment(dto.text());
       comments.setUpdatedAt(LocalDateTime.now());

        commentsMapper.updateCommentFromDto(dto, comments, userRepository, carRepository);

        commentsRepository.save(comments);
        return new ApiResponse(true, "Comment successfully updated!");

    }

    @Override
    public ApiResponse deleteComment(UUID commentId) {
        Comments comments = commentsRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment with id " + commentId + " not found!"));

        UUID currentUserId = auditorAware.getCurrentAuditor()
                .orElseThrow(() -> new RuntimeException("Current user not found!"));

        if(!comments.getUser().getId().equals(currentUserId)) {
            throw new RuntimeException("You can only delete your own comments!");
        }

        commentsRepository.deleteById(commentId);
        return new ApiResponse(true, "Comment successfully deleted!");
    }
}
