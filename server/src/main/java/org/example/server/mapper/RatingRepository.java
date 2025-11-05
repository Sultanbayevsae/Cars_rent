package org.example.server.mapper;

import org.example.server.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RatingRepository extends JpaRepository<Rating, UUID> {
    boolean findByCar_IdAndCar_Id(UUID user, UUID uuid);

    boolean existsByUser_IdAndComments_Id(UUID user, UUID uuid);

    List<Rating> findAllByCar_Id(UUID carId);

    Double getAverageRatingByCarId(UUID carId);

    Double getAverageRatingByCommnetsId(UUID commentsId);
}
