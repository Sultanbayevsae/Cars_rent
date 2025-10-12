package org.example.server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.server.validation.region.ValidRegionId;

public record RegionUpdater(@ValidRegionId
                            Long id,
                            @NotBlank(message = "Region name bo`sh bo`lmasligi kerak")
                            @Size(min = 3, message = "Region name ning uzunligi kamida 3 bo`lishi kerak")
                            String name) {
}
