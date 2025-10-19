package org.example.server.mapper;

import org.example.server.dto.BranchCreator;
import org.example.server.dto.BranchResponse;
import org.example.server.dto.BranchUpdater;
import org.example.server.entity.Branch;
import org.example.server.repository.BranchRepository;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "car", ignore = true)
    Branch toEntity(BranchCreator creator);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "car", ignore = true)
    void updateBranchFromDto(BranchUpdater updater, @MappingTarget Branch branch);

    BranchResponse toResponse(Branch branch);
}
