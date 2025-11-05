package org.example.server.repository;

import org.example.server.entity.LikedCars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LikedCarRepository extends JpaRepository<LikedCars, UUID> {
    boolean existsByUserIdAndCarId(UUID userId, UUID carId);

    List<LikedCars> findAllByUserId(UUID userId);

    void deleteAllByUserId(UUID userId);

}
