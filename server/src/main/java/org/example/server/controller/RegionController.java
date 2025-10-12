package org.example.server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.server.dto.ApiResponse;
import org.example.server.dto.RegionCreator;
import org.example.server.dto.RegionUpdater;
import org.example.server.exception.ErrorDTO;
import org.example.server.service.RegionService;
import org.example.server.utill.AppConstants;
import org.example.server.validation.region.ValidRegionId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RegionController.BaseUrl)
@Tag(
        name = "RegionController",
        description = "Bu yerda Region entity uchun CRUD API lar mavjud"
)
@Validated
public class RegionController {

    public static final String BaseUrl = AppConstants.BASE_URL + "/region";

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }


    @Operation(
            description = "Yangi Region yaratish uchun ishlatilinadi",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "201",
                            description = "Region Yaratildi!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "417",
                            description = "Region name takrorlangan❌",
                            content = @Content(
                                    schema = @Schema(implementation = ApiResponse.class)
                            )
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createRegion(@RequestBody @Valid RegionCreator creator) {
        ApiResponse response = regionService.createRegion(creator);
        return response.getSuccess()
                ?
                ResponseEntity.status(HttpStatus.CREATED).body(response)
                :
                ResponseEntity.status(417).body(response)
                ;
    }


    @Operation(
            description = "Region ni delete qilish uchun. RegionId path variable da berib yuborilishligi kerak.",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "204",
                            description = "Region Deleted!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "417",
                            description = "Region name takrorlangan❌",
                            content = @Content(
                                    schema = @Schema(implementation = ApiResponse.class)
                            )
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteRegion(@PathVariable @ValidRegionId Long id) {
        ApiResponse response = regionService.deleteRegionById(id);
        return response.getSuccess()
                ?
                ResponseEntity.status(204).body(response)
                :
                ResponseEntity.status(417).body(response)
                ;
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateRegion(@RequestBody @Valid RegionUpdater updator){
        ApiResponse response = regionService.updateRegion(updator);
        return response.getSuccess()
                ?
                ResponseEntity.status(200).body(response)
                :
                ResponseEntity.status(417).body(response)
                ;
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable @ValidRegionId Long id){
        ApiResponse response = regionService.findById(id);
        return response.getSuccess()
                ?
                ResponseEntity.status(200).body(response)
                :
                ResponseEntity.status(417).body(response)
                ;
    }

}
