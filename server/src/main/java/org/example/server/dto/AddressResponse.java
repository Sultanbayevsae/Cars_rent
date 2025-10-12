package org.example.server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record AddressResponse(@JsonProperty("address_id")
                              UUID id,
                              @JsonProperty("address_details")
                              String details,
                              @JsonProperty("district_details")
                              DistrictResponse district) {
}
