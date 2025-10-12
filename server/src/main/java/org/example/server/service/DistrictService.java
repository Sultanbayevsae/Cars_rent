package org.example.server.service;

import org.example.server.dto.ApiResponse;
import org.example.server.dto.DistrictCreator;

import java.util.UUID;

public interface DistrictService {

    ApiResponse createDistrict(DistrictCreator creator);

    ApiResponse getAllDistricts();

    ApiResponse getDistrictById(UUID id);

    ApiResponse updateDistrict(UUID id, DistrictCreator creator);

    ApiResponse deleteDistrict(UUID id);
}

