package org.example.server.repository;

import org.example.server.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RatingRepository extends JpaRepository<Rating, UUID> {
    boolean findByCar_IdAndCar_Id(UUID user, UUID uuid);

    boolean existsByUser_IdAndComments_Id(UUID user, UUID uuid);

    List<Rating> findAllByCar_Id(UUID carId);

    Double findAverageRatingByCarId(UUID carId);

    @Query("SELECT AVG(r.rating) FROM Rating r WHERE r.comments.id = :commentId")
    Double getAverageRatingByCommnentsId(UUID commentsId);
}
