package org.example.server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.server.dto.ApiResponse;
import org.example.server.dto.BranchCreator;
import org.example.server.dto.BranchUpdater;
import org.example.server.exception.ErrorDTO;
import org.example.server.repository.BranchRepository;
import org.example.server.service.BranchService;
import org.example.server.utill.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Branch Controller", description = "APIs for managing branches")
@RestController
@RequestMapping(BranchController.BaseUrl)
public class BranchController {
    public static final String BaseUrl = AppConstants.BASE_URL + "/branch";
    private final BranchRepository branchRepository;
    private final BranchService branchService;

    public BranchController(BranchRepository branchRepository, BranchService branchService) {
        this.branchRepository = branchRepository;
        this.branchService = branchService;
    }

    @Operation(
            description = "Create a new Branch",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "201",
                            description = "Branch created!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "500",
                            description = "HTTP Status Internal Server Error"
                    )
            }
    )
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid BranchCreator branchCreator) {
        System.out.println("=== BRANCH/CREATE DEBUG ===");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("User: " + (auth != null ? auth.getName() : "NULL"));
        System.out.println("Authenticated: " + (auth != null ? auth.isAuthenticated() : "false"));
        if (auth != null) {
            System.out.println("Roles: " + auth.getAuthorities());
        }
        System.out.println("==========================");

        ApiResponse response = branchService.createBranch(branchCreator);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            description = "Get Branch by ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Branch retrieved!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "Branch not found!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "500",
                            description = "HTTP Status Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorDTO.class)
                            )
                    )
            }
    )
    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable UUID id) {
        ApiResponse response = branchService.getBranchById(id);
        if(response.getSuccess()){
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @Operation(
            description = "Get all Branches",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Branches retrieved!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "Branches not found!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "500",
                            description = "HTTP Status Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorDTO.class)
                            )
                    )
            }
    )
    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse> getAll() {
        ApiResponse response = branchService.getAllBranches();
        if(response.getSuccess()){
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @Operation(
            description = "Delete Branch by ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "204",
                            description = "Branch deleted!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "Branch not found!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "500",
                            description = "HTTP Status Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorDTO.class)
                            )
                    )
            }
    )

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable UUID id) {
        ApiResponse response = branchService.deleteBranchById(id);
        if(response.getSuccess()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @Operation(
            description = "Update an existing Branch",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Branch updated!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "Branch not found!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "500",
                            description = "HTTP Status Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorDTO.class)
                            )
                    )
            }
    )
    @PutMapping("/update")
    public ResponseEntity<ApiResponse> update(@RequestBody @Valid BranchUpdater updater) {
        return ResponseEntity.ok(branchService.updateBranch(updater));
    }




}
