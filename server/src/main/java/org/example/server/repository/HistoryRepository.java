package org.example.server.repository;

import org.example.server.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findByUserId(UUID userId);
}
