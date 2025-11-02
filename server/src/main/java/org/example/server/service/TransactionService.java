package org.example.server.service;

import org.example.server.dto.TransactionRequest;
import org.example.server.dto.TransactionResponse;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
    TransactionResponse create(TransactionRequest request);
    TransactionResponse getById(UUID id);
    List<TransactionResponse> getAll();
    TransactionResponse  updateStatus(UUID id, String status);
    void delete(UUID id);
}
