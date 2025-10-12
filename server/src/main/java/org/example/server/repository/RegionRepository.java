package org.example.server.repository;

import org.example.server.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
    Boolean existsRegionByName(String name);
}
