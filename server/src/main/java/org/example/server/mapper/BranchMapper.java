package org.example.server.mapper;

import org.example.server.dto.BranchCreator;
import org.example.server.dto.BranchResponse;
import org.example.server.dto.BranchUpdater;
import org.example.server.entity.Address;
import org.example.server.entity.Branch;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "car", ignore = true)
    Branch toEntity(BranchCreator creator);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "car", ignore = true)
    void updateBranchFromDto(BranchUpdater updater, @MappingTarget Branch branch);

    @Named("updateAddress")
    default void updateAddress(BranchUpdater updater, @MappingTarget Address address) {
        if (updater.CityOrTown() != null) {
            address.setCityOrTown(updater.CityOrTown());
        }
        if (updater.detailAddress() != null) {
            address.setDetails(updater.detailAddress());
        }
    }
    @Mapping(target = "address", source = "branch.address")
    BranchResponse toResponse(Branch branch);}
