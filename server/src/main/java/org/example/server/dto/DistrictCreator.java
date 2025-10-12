package org.example.server.dto;

import jakarta.validation.constraints.NotBlank;
import org.example.server.validation.region.ValidRegionId;

public record DistrictCreator(
        @NotBlank(message = "District name must not be blank")
        String name,
        @ValidRegionId
        Long regionId) {
}
