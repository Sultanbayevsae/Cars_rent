package org.example.server.repository;

import org.example.server.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByUserId(UUID userId);
    List<Rental> findByEndDateBetween(LocalDateTime from, LocalDateTime to);
}


