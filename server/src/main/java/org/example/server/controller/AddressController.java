package org.example.server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.server.dto.AddressCreator;
import org.example.server.dto.ApiResponse;
import org.example.server.exception.ErrorDTO;
import org.example.server.service.AddressService;
import org.example.server.utill.AppConstants;
import org.example.server.validation.address.ValidAddressId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(AddressController.BaseUrl)
@Tag(
        name = "AddressController",
        description = "Bu yerda Address entity uchun CRUD API lar mavjud")
@Validated
public class AddressController {
    public static final String BaseUrl = AppConstants.BASE_URL + "/address";
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @Operation(
            description = "Yangi Address yaratish uchun ishlatiladi",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "201",
                            description = "Address Yaratildi!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "417",
                            description = "Address yaratishda xatolik❌",
                            content = @Content(schema = @Schema(implementation = ApiResponse.class))
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))
                    )
            }
    )
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createAddress(@RequestBody @Valid AddressCreator creator) {
        ApiResponse response = addressService.createAddress(creator);
        return response.getSuccess()
                ? ResponseEntity.status(201).body(response)
                : ResponseEntity.status(417).body(response);
    }


    @Operation(
            description = "Barcha Addresslarni olish uchun API",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Houses found!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "Houses not found!"
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/findAll")
    public ResponseEntity<ApiResponse> getAllAddresses() {
        return ResponseEntity.ok(addressService.getAllAddresses());
    }


    @Operation(
            description = "Addressni ID orqali olish uchun API",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "House deleted!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "House not found!"
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse> getAddressById(@PathVariable @ValidAddressId UUID id) {
        ApiResponse response = addressService.getAddressById(id);
        return response.getSuccess()
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(404).body(response);
    }


    @Operation(
            description = "Addressni yangilash uchun API",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "House Updated!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "House not found!"
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateAddress(
            @PathVariable UUID id,
            @RequestBody @Valid AddressCreator creator
    ) {
        ApiResponse response = addressService.updateAddress(id, creator);
        return response.getSuccess()
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(417).body(response);
    }


    @Operation(
            description = "Addressni o‘chirish uchun API",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "204",
                            description = "House deleted!"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "House not found!"
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable @ValidAddressId UUID id) {
        ApiResponse response = addressService.deleteAddress(id);
        return response.getSuccess()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.status(417).build();
    }
}
