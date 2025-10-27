package org.example.server.repository;

import org.example.server.entity.LikedCars;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LikedCarRepository extends JpaRepository<LikedCars, UUID> {
    boolean existsByUserIdAndCarId(UUID userId, UUID carId);

    List<LikedCars> findAllByUserId(UUID userId);

    void deleteAllByUserId(UUID userId);

}
