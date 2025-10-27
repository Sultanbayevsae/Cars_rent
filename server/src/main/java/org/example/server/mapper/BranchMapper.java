package org.example.server.mapper;

import org.example.server.dto.BranchCreator;
import org.example.server.dto.BranchResponse;
import org.example.server.dto.BranchUpdater;
import org.example.server.entity.Address;
import org.example.server.entity.Branch;
import org.example.server.repository.AddressRespository;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface BranchMapper {

//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "cars", ignore = true)
//    @Mapping(target = "address", source = "creator.addressId", qualifiedByName = "mapAddress")
    Branch toEntity(BranchCreator creator, @Context AddressRespository addressRepository);

    @Named("mapAddress")
    default Address mapAddress(UUID addressId, @Context AddressRespository addressRepository) {
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found by id: " + addressId));
    }

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    @Mapping(target = "cars", ignore = true)
//    @Mapping(target = "address", source = "addressId", qualifiedByName = "mapAddressById")
    void updateBranchFromDto(BranchUpdater updater, @MappingTarget Branch branch, @Context AddressRespository addressRepository);

    @Named("mapAddressById")
    default Address mapAddressById(UUID addressId, @Context AddressRespository addressRepository) {
        if (addressId == null) {
            return null;
        }
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + addressId));
    }

    List<BranchResponse> toResponse(List<Branch> branches);

//    @Mapping(target = "id", source = "branch.id")
//    @Mapping(target = "branchName", source = "branch.branchName")
//    @Mapping(target = "addressId", source = "branch.address.id")
//    @Mapping(target = "cityOrTown", source = "branch.address.cityOrTown")
//    @Mapping(target = "detailAddress", source = "branch.address.details")
    BranchResponse toResponse(Branch branch);
}
