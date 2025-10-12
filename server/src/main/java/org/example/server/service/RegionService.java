package org.example.server.service;

import jakarta.validation.Valid;
import org.example.server.dto.ApiResponse;
import org.example.server.dto.RegionCreator;
import org.example.server.dto.RegionUpdater;

public interface RegionService {
    ApiResponse createRegion(@Valid RegionCreator creator); // specification

    ApiResponse deleteRegionById(Long id);

    ApiResponse updateRegion(RegionUpdater updator);

    ApiResponse findById(Long id);
}
