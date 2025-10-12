package org.example.server.repository;

import org.example.server.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DistrictRepository extends JpaRepository<District, UUID> {
}