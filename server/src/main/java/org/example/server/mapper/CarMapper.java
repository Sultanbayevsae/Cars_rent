package org.example.server.mapper;

import org.example.server.dto.CarCreator;
import org.example.server.entity.Branch;
import org.example.server.entity.Car;
import org.example.server.repository.BranchRepository;
import org.example.server.repository.CarRepository;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CarMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "branch", source = "creator.branchId", qualifiedByName = "mapBranch")
    Car toEntity(CarCreator creator, @Context BranchRepository branchRepository);



    @Named("mapBranch")
    default Branch mapBranch(UUID branchId, @Context BranchRepository repository) {
        return branchId == null ? null : repository.findById(branchId)
                .orElseThrow(() -> new RuntimeException("Branch not found: " + branchId));
    }


}
