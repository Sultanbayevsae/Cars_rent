package org.example.server.service.impl;

import org.example.server.dto.AddressCreator;
import org.example.server.dto.AddressResponse;
import org.example.server.dto.ApiResponse;
import org.example.server.entity.Address;
import org.example.server.entity.District;
import org.example.server.entity.Region;
import org.example.server.mapper.AddressMapper;
import org.example.server.repository.AddressRepository;
import org.example.server.repository.DistrictRepository;
import org.example.server.repository.RegionRepository;
import org.example.server.service.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressImplService implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final DistrictRepository districtRepository;
    private final RegionRepository regionRepository;

    public AddressImplService(AddressRepository addressRepository, AddressMapper addressMapper, DistrictRepository districtRepository, RegionRepository regionRepository) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
        this.districtRepository = districtRepository;
        this.regionRepository = regionRepository;
    }


    @Override
    @Transactional
    public ApiResponse createAddress(AddressCreator creator) {
        Address entity = addressMapper.toEntity(creator, regionRepository, districtRepository);
        addressRepository.save(entity);
        return new ApiResponse(true, "Address yaratildi ✅");
    }

    @Override
    public ApiResponse getAllAddresses() {
        List<Address> addressList = addressRepository.findAll();
        List<AddressResponse>addresses = addressMapper.toDTO(addressList);
        return new ApiResponse(true, "Barcha addresslar olindi ✅", addresses);
    }



    @Override
    public ApiResponse getAddressById(UUID id) {
        return addressRepository.findById(id)
                .map(address -> new ApiResponse(true, "Muvaffaqiyatli topildi", address))
                .orElseGet(() -> new ApiResponse(false, "Address topilmadi"));
    }

    @Override
    public ApiResponse deleteAddress(UUID id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()) {
            return new ApiResponse(false, "Address topilmadi");
        }

        addressRepository.deleteById(id);

        return new ApiResponse(true, "Address muvaffaqiyatli o'chirildi");
    }

    @Override
    public ApiResponse updateAddress(UUID id, AddressCreator creator) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()) {
            return new ApiResponse(false, "Address topilmadi");
        }

        Optional<District> districtOptional = districtRepository.findById(creator.districtId());
        if (districtOptional.isEmpty()) {
            return new ApiResponse(false, "District topilmadi");
        }

        Optional<Region> regionOptional = regionRepository.findById(creator.regionId());
        if (optionalAddress.isEmpty()) {
            return new ApiResponse(false, "Region topilmadi");
        }
        Address address = optionalAddress.get();
        address.setDetails(creator.details());
        address.setDistrict(districtOptional.get());
        address.setRegion(regionOptional.get());

        Address updatedAddress = addressRepository.save(address);

        return new ApiResponse(true, "Address muvaffaqiyatli yangilandi", updatedAddress);
    }
}
