package org.example.server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RegionResponse (@JsonProperty("region_id")
                              Long id,
                              @JsonProperty("region_name")
                              String name){
}
