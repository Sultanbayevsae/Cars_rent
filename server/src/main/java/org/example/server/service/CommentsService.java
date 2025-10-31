package org.example.server.service;

import jakarta.validation.Valid;
import org.example.server.dto.ApiResponse;
import org.example.server.dto.CommentUpdateDTO;
import org.example.server.dto.CommentsCreator;
import org.example.server.repository.CarRepository;
import org.example.server.repository.UserRepository;

import java.util.UUID;

public interface CommentsService {
    ApiResponse createComment(@Valid CommentsCreator dto);

    ApiResponse getCommentById(UUID commentId);

    ApiResponse getAllCommentsByCarId(UUID carId);

    ApiResponse updateComment(UUID commentId, @Valid CommentUpdateDTO dto);

    ApiResponse deleteComment(UUID commentId);
}
