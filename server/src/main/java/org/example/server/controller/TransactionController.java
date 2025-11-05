package org.example.server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.server.dto.TransactionRequest;
import org.example.server.dto.TransactionResponse;
import org.example.server.service.TransactionService;
import org.example.server.utill.AppConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(AppConstants.BASE_URL + "/transactions")
@Tag(
        name = "Transaction Controller",
        description = "Full Transaction management CRUD APIs"
)
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    @Operation(summary = "Create a new transaction")
    public ResponseEntity<TransactionResponse> create(@RequestBody TransactionRequest request){
        return ResponseEntity.ok(transactionService.create(request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get transaction by ID")
    public ResponseEntity<TransactionResponse> getById(@PathVariable UUID id){
        return ResponseEntity.ok(transactionService.getById(id));
    }


    @GetMapping
    @Operation(summary = "Get all transactions")
    public ResponseEntity<List<TransactionResponse>> getAll(){
        return ResponseEntity.ok(transactionService.getAll());
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Update transaction status by ID")
    public ResponseEntity<TransactionResponse> updateStatus(@PathVariable UUID id, @RequestParam String status){
        return ResponseEntity.ok(transactionService.updateStatus(id, status));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete transaction by ID")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        transactionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
