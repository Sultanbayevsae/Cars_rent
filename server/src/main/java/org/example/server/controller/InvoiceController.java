package org.example.server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.server.dto.InvoiceRequestDTO;
import org.example.server.dto.InvoiceResponseDTO;
import org.example.server.entity.Invoice;
import org.example.server.mapper.InvoiceMapper;
import org.example.server.service.InvoiceService;
import org.example.server.utill.AppConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(AppConstants.BASE_URL + "/invoices")
@Tag(
        name = "Invoice Controller",
        description = "CRUD APIs for invoice management"
)
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final InvoiceMapper invoiceMapper;

    @Operation(summary = "Create new invoice")
    @PostMapping
    public ResponseEntity<InvoiceResponseDTO> createInvoice(@RequestBody InvoiceRequestDTO dto) {
        Invoice invoice = invoiceService.createInvoice(dto);
        return ResponseEntity.ok(invoiceMapper.toResponse(invoice));
    }

    @Operation(summary = "Get all invoices by user id")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<InvoiceResponseDTO>> getInvoicesByUser(@PathVariable UUID userId){
        List<InvoiceResponseDTO> list = invoiceService.getInvoiceByUserId(userId)
                .stream().map(invoiceMapper::toResponse).toList();
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Mark invoice as paid")
    @PutMapping("/{id}/pay")
    public ResponseEntity<InvoiceResponseDTO> markAsPaid(@PathVariable UUID id){
        Invoice invoice = invoiceService.markAsPaid(id);
        return ResponseEntity.ok(invoiceMapper.toResponse(invoice));
    }
}
