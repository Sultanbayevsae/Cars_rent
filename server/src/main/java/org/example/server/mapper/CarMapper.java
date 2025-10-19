package org.example.server.mapper;

import org.example.server.dto.CarCreator;
import org.example.server.dto.CarUpdater;
import org.example.server.entity.Branch;
import org.example.server.entity.Car;
import org.example.server.repository.BranchRepository;
import org.example.server.repository.CarRepository;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CarMapper {


//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "branch", source = "creator.branchId", qualifiedByName = "mapBranch")
//    @Mapping(target = "photos", ignore = true)
//    @Mapping(target = "comments", ignore = true)
    Car toEntity(CarCreator creator, @Context BranchRepository branchRepository);



    @Named("mapBranch")
    default Branch mapBranch(UUID branchId, @Context BranchRepository repository) {
        return branchId == null ? null : repository.findById(branchId)
                .orElseThrow(() -> new RuntimeException("Branch not found: " + branchId));
    }


//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    @Mapping(target = "branch", source = "updater.branch", qualifiedByName = "mapBranchEntity")
//    @Mapping(target = "photos", ignore = true)
//    @Mapping(target = "comments", ignore = true)
    void updateCarFromDto(CarUpdater updater, @MappingTarget Car car, @Context BranchRepository branchRepository);


    @Named("mapBranchEntity")
    default Branch mapBranchEntity(Branch branch, @Context BranchRepository repository) {
        if(branch == null) return null;
        return repository.findById(branch.getId())
                .orElseThrow(() -> new RuntimeException("Branch not found: " + branch.getId()));
    }


}
