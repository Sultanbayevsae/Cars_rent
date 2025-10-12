package org.example.server.dto;

import jakarta.validation.constraints.NotBlank;
import org.example.server.validation.district.ValidDistrictId;
import org.example.server.validation.region.ValidRegionId;

import java.util.UUID;

public record AddressCreator(@NotBlank(message = "Street name must not be blank")
                             String details,
                             @ValidRegionId
                             Long regionId,
                             @ValidDistrictId
                             UUID districtId) {
}
