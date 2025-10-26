package org.example.server.service.impl;

import org.example.server.dto.ApiResponse;
import org.example.server.dto.BranchCreator;
import org.example.server.dto.BranchResponse;
import org.example.server.dto.BranchUpdater;
import org.example.server.entity.Branch;
import org.example.server.mapper.BranchMapper;
import org.example.server.repository.BranchRepository;
import org.example.server.service.BranchService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BranchImplService implements BranchService {

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;

    public BranchImplService(BranchRepository branchRepository, BranchMapper branchMapper) {
        this.branchRepository = branchRepository;
        this.branchMapper = branchMapper;
    }


    @Override
    @Transactional
    public ApiResponse createBranch(BranchCreator branchCreator) {
        Branch entity = branchMapper.toEntity(branchCreator);
        branchRepository.save(entity);
        return new ApiResponse(true, "Branch has successfully been created!");
    }

    @Override
    public ApiResponse getAllBranches() {
        List<Branch> branches = branchRepository.findAll();
        if (branches.isEmpty()) {
            return new ApiResponse(false, "No branches found!");
        }
        Set<BranchResponse> branchResponses = branches
                .stream().map(branchMapper::toResponse).collect(Collectors.toSet());
        return new ApiResponse(true, "Branches retrieved successfully!", branchResponses);
    }

    @Override
    public ApiResponse getBranchById(UUID branchId) {
        Optional<Branch> branch = branchRepository.findById(branchId);
        return branch.map(value -> {
            BranchResponse response = branchMapper.toResponse(value);
            return new ApiResponse(true, "Branch retrieved!", response);
        }).orElseGet(() -> new ApiResponse(false, "Branch not found!"));
    }

    @Override
    public ApiResponse deleteBranchById(UUID branchId) {
        if (!branchRepository.existsById(branchId)) {
            return new ApiResponse(false, "Branch does not exist!");
        }
        branchRepository.deleteById(branchId);
        return new ApiResponse(true, "Branch has been successfully deleted!");
    }

    @Override
    public ApiResponse updateBranch(BranchUpdater updater) {
        Branch branch = branchRepository.findById(updater.id())
                .orElseThrow(() -> new RuntimeException("Branch with ID " + updater.id() + " not found"));
        branchMapper.updateBranchFromDto(updater, branch);

        return new ApiResponse(true, "Branch successfully updated!");
    }
}
