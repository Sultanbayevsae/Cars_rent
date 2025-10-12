package org.example.server.mapper;

import org.example.server.dto.RegionCreator;
import org.example.server.dto.RegionResponse;
import org.example.server.dto.RegionUpdater;
import org.example.server.entity.Region;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RegionMapper {

    Region toEntity(RegionCreator creator);

    //    @Mapping(target = "id", ignore = true)
    void toEntity(@MappingTarget Region region, RegionUpdater updater);

    RegionResponse toDto(Region region);
}
