package org.example.server.mapper;

import org.example.server.dto.DistrictCreator;
import org.example.server.entity.District;
import org.example.server.entity.Region;
import org.example.server.repository.RegionRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DistrictMapper {

        @Mapping(target = "id", ignore = true)
    @Mapping(target = "region", source = "creator.regionId", qualifiedByName = "mapRegion")
    District toEntity(DistrictCreator creator, @Context RegionRepository regionRepository);

    @Named("mapRegion")
    default Region toRegion(Long regionId, @Context RegionRepository regionRepository){
        return regionRepository.findById(regionId).orElseThrow(()->new RuntimeException("Region not found by id: "+regionId));
    };
}
