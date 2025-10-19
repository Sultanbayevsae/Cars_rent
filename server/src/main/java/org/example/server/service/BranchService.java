package org.example.server.service;

import jakarta.validation.Valid;
import org.example.server.dto.ApiResponse;
import org.example.server.dto.BranchCreator;
import org.example.server.dto.BranchUpdater;

import java.util.UUID;

public interface BranchService {

    ApiResponse createBranch(@Valid BranchCreator branchCreator);

    ApiResponse getAllBranches();

    ApiResponse getBranchById(UUID branchId);

    ApiResponse deleteBranchById(UUID branchId);

    ApiResponse updateBranch(BranchUpdater branchUpdater);
}
