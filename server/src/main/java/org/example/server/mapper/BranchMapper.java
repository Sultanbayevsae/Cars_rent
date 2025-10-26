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
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "car", ignore = true)
    @Mapping(target = "address", source = "updater", qualifiedByName = "updateAddress")
    void updateBranchFromDto(BranchUpdater updater, @MappingTarget Branch branch);

    @Named("updateAddress")
    default void updateAddress(BranchUpdater updater, @MappingTarget Address address) {
        if (updater.cityOrTown() != null) {
            address.setCityOrTown(updater.cityOrTown());
        }
        if (updater.details() != null) {
            address.setDetails(updater.details());
        }
    }
    @Mapping(target = "address", source = "branch.address")
    BranchResponse toResponse(Branch branch);}
