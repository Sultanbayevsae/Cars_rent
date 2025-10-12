package org.example.server.mapper;

import org.example.server.dto.AddressCreator;
import org.example.server.dto.AddressResponse;
import org.example.server.entity.Address;
import org.example.server.entity.District;
import org.example.server.entity.Region;
import org.example.server.repository.DistrictRepository;
import org.example.server.repository.RegionRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    //    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "region", source = "creator.regionId", qualifiedByName = "mapRegion")
//    @Mapping(target = "district", source = "creator.districtId", qualifiedByName = "mapDistrict")
    Address toEntity(AddressCreator creator, @Context RegionRepository regionRepository, @Context DistrictRepository districtRepository);


    @Named("mapRegion")
    default Region toRegion(Long regionId, @Context RegionRepository regionRepository) {
        return regionRepository.findById(regionId)
                .orElseThrow(() -> new RuntimeException("Region not found by id: " + regionId));
    }

    @Named("mapDistrict")
    default District toDistrict(UUID districtId, @Context DistrictRepository districtRepository) {
        return districtRepository.findById(districtId)
                .orElseThrow(() -> new RuntimeException("District not found by id: " + districtId));
    }

    //    @Mapping(target = "id", source = "id")
//    @Mapping(target = "details", source = "details")
//    @Mapping(target = "district.id", source = "address.district.id")
//    @Mapping(target = "district.name", source = "address.district.name")
//    @Mapping(target = "district.region.id", source = "address.district.region.id")
//    @Mapping(target = "district.region.name", source = "address.district.region.name")
    AddressResponse toDTO(Address address);


    //    @IterableMapping(qualifiedByName = "toDTO")
    List<AddressResponse> toDTO(List<Address> addressList);
}
