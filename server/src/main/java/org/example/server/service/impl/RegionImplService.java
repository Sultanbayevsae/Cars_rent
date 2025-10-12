package org.example.server.service.impl;

import org.example.server.dto.ApiResponse;
import org.example.server.dto.RegionCreator;
import org.example.server.dto.RegionResponse;
import org.example.server.dto.RegionUpdater;
import org.example.server.entity.Region;
import org.example.server.mapper.RegionMapper;
import org.example.server.repository.RegionRepository;
import org.example.server.service.RegionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RegionImplService implements RegionService {
    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;

    public RegionImplService(RegionRepository regionRepository, RegionMapper regionMapper) {
        this.regionRepository = regionRepository;
        this.regionMapper = regionMapper;
    }

    @Override
    @Transactional
    public ApiResponse createRegion(RegionCreator creator) { // implementation
        Region entity = regionMapper.toEntity(creator);
        regionRepository.save(entity);
        return new ApiResponse(true, "Region created!");
    }

    @Override
    @Transactional
    public ApiResponse deleteRegionById(Long id) {
        try {
            regionRepository.deleteById(id);
            return new ApiResponse(true, "Region deleted!");
        } catch (Exception e) {
            return new ApiResponse(false, "Error deleting region");
        }
    }

    @Override
    @Transactional
    public ApiResponse updateRegion(RegionUpdater updater) {
        Optional<Region> regionOptional = regionRepository.findById(updater.id());
        if (regionOptional.isPresent()) {
            Region region = regionOptional.get();
            regionMapper.toEntity(region, updater);
            return new ApiResponse(true, "Region updated!");
        } else {
            return new ApiResponse(false, "Region not found by id: " + updater.id());
        }
    }

    @Override
    public ApiResponse findById(Long id) {
        Region region = regionRepository.findById(id).get();
        RegionResponse dto = regionMapper.toDto(region);
        return new ApiResponse(true, "Region details", dto);
    }

//    private Region toEntity(RegionCreator creator) {
//        Region region = new Region();
//        region.setName(creator.name());
//        return region;
//    }
}
