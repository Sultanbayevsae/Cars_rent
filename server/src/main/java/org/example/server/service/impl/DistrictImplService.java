package org.example.server.service.impl;

import org.example.server.dto.ApiResponse;
import org.example.server.dto.DistrictCreator;
import org.example.server.dto.DistrictResponse;
import org.example.server.dto.RegionResponse;
import org.example.server.entity.District;
import org.example.server.entity.Region;
import org.example.server.mapper.DistrictMapper;
import org.example.server.repository.DistrictRepository;
import org.example.server.repository.RegionRepository;
import org.example.server.service.DistrictService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DistrictImplService implements DistrictService {

    private final DistrictRepository districtRepository;
    private final RegionRepository regionRepository;
    private final DistrictMapper districtMapper;

    public DistrictImplService(DistrictRepository districtRepository,
                               RegionRepository regionRepository, DistrictMapper districtMapper) {
        this.districtRepository = districtRepository;
        this.regionRepository = regionRepository;
        this.districtMapper = districtMapper;
    }

    @Override
    @Transactional
    public ApiResponse createDistrict(DistrictCreator creator) {
        District entity = districtMapper.toEntity(creator, regionRepository);
        districtRepository.save(entity);
        return new ApiResponse(true, "District yaratildi ✅");
    }


    @Override
    public ApiResponse getAllDistricts() {
        List<District> districtList = districtRepository.findAll();
        List<DistrictResponse> districts = toDtoList(districtList);
        return new ApiResponse(true, "Barcha districtlar olindi ✅", districts);
    }

    private List<DistrictResponse> toDtoList(List<District> districtList) {
        return districtList.stream().map(
                district -> new DistrictResponse(
                        district.getId(),
                        district.getName(),
                        new RegionResponse(
                                district.getRegion().getId(),
                                district.getRegion().getName()
                        )
                )).toList();
    }

    @Override
    public ApiResponse getDistrictById(UUID id) {
        return districtRepository.findById(id)
                .map(district -> new ApiResponse(true, "District topildi ✅", district))
                .orElseGet(() -> new ApiResponse(false, "District topilmadi ❌"));
    }

    @Override
    public ApiResponse updateDistrict(UUID id, DistrictCreator creator) {
        Optional<District> optionalDistrict = districtRepository.findById(id);
        if (optionalDistrict.isEmpty()) {
            return new ApiResponse(false, "District topilmadi ❌");
        }

        Optional<Region> regionOptional = regionRepository.findById(creator.regionId());
        if (regionOptional.isEmpty()) {
            return new ApiResponse(false, "Region topilmadi ❌");
        }

        District district = optionalDistrict.get();
        district.setName(creator.name());
        district.setRegion(regionOptional.get());

        District updatedDistrict = districtRepository.save(district);
        return new ApiResponse(true, "District yangilandi ✅", updatedDistrict);
    }

    @Override
    public ApiResponse deleteDistrict(UUID id) {
        Optional<District> optionalDistrict = districtRepository.findById(id);
        if (optionalDistrict.isEmpty()) {
            return new ApiResponse(false, "District topilmadi ❌");
        }

        districtRepository.deleteById(id);
        return new ApiResponse(true, "District o‘chirildi ✅");
    }
}
