package org.example.server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.server.dto.ApiResponse;
import org.example.server.dto.DistrictCreator;
import org.example.server.exception.ErrorDTO;
import org.example.server.service.DistrictService;
import org.example.server.utill.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(DistrictController.BaseUrl)
@Tag(
        name = "DistrictController",
        description = "Bu yerda District entity uchun CRUD API lar mavjud"
)
@Validated
public class DistrictController {

    public static final String BaseUrl = AppConstants.BASE_URL + "/district";

    private final DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    // CREATE
    @Operation(
            description = "Yangi District yaratish uchun ishlatiladi",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "201",
                            description = "District Yaratildi!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "417",
                            description = "District yaratishda xatolik❌",
                            content = @Content(schema = @Schema(implementation = ApiResponse.class))
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))
                    )
            }
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createDistrict(@RequestBody @Valid DistrictCreator creator) {
        ApiResponse response = districtService.createDistrict(creator);
        return response.getSuccess()
                ? ResponseEntity.status(HttpStatus.CREATED).body(response)
                : ResponseEntity.status(417).body(response);
    }

    @Operation(description = "Barcha Districtlarni olish uchun API")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/findAll")
    public ResponseEntity<ApiResponse> getAllDistricts() {
        ApiResponse response = districtService.getAllDistricts();
        return ResponseEntity.ok(response);
    }

    @Operation(description = "Districtni ID orqali olish uchun API")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getDistrictById(@PathVariable UUID id) {
        ApiResponse response = districtService.getDistrictById(id);
        return response.getSuccess()
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(404).body(response);
    }

    @Operation(description = "Districtni yangilash uchun API")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateDistrict(
            @PathVariable UUID id,
            @RequestBody @Valid DistrictCreator creator
    ) {
        ApiResponse response = districtService.updateDistrict(id, creator);
        return response.getSuccess()
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(417).body(response);
    }

    @Operation(description = "Districtni o‘chirish uchun API")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteDistrict(@PathVariable UUID id) {
        ApiResponse response = districtService.deleteDistrict(id);
        return response.getSuccess()
                ? ResponseEntity.status(204).body(response)
                : ResponseEntity.status(417).body(response);
    }
}
