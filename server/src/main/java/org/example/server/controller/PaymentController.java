package org.example.server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.server.dto.PaymentRequestDTO;
import org.example.server.dto.PaymentResponseDTO;
import org.example.server.entity.PaymentStatus;
import org.example.server.service.PaymentService;
import org.example.server.utill.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(AppConstants.BASE_URL + "/payments")
@Tag(
        name = "Payment Controller",
        description = "Payment management APIs"
)
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "Create a new payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Payment created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaymentResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Rental not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<PaymentResponseDTO> createPayment(@RequestBody PaymentRequestDTO dto) {
        PaymentResponseDTO response = paymentService.createPayment(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Get payment by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaymentResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Payment not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> getPaymentById(@PathVariable Long id) {
        PaymentResponseDTO response = paymentService.findPaymentById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get all payments of a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of payments",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaymentResponseDTO.class)))
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PaymentResponseDTO>> getPaymentsByUser(@PathVariable UUID userId) {
        List<PaymentResponseDTO> payments = paymentService.getPaymentsByUserId(userId);
        return ResponseEntity.ok(payments);
    }

    @Operation(summary = "Update payment status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment status updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaymentResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Payment not found",
                    content = @Content)
    })
    @PatchMapping("/{id}/status")
    public ResponseEntity<PaymentResponseDTO> updatePaymentStatus(
            @PathVariable Long id,
            @RequestParam PaymentStatus status) {
        PaymentResponseDTO response = paymentService.updatePaymentStatus(id, status);
        return ResponseEntity.ok(response);
    }
}
