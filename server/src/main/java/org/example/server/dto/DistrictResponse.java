package org.example.server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record DistrictResponse(@JsonProperty("district_id")
                               UUID id,
                               @JsonProperty("district_name")
                               String name,
                               @JsonProperty("region_details")
                               RegionResponse region) {
}
