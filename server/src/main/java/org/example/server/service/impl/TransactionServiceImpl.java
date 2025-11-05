package org.example.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.server.dto.TransactionRequest;
import org.example.server.dto.TransactionResponse;
import org.example.server.entity.Payment;
import org.example.server.entity.PaymentCard;
import org.example.server.entity.Transaction;
import org.example.server.entity.TransactionStatus;
import org.example.server.mapper.TransactionMapper;
import org.example.server.repository.PaymentCardRepository;
import org.example.server.repository.PaymentRepository;
import org.example.server.repository.TransactionRepository;
import org.example.server.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper mapper;
    private final PaymentRepository paymentRepository;
    private final PaymentCardRepository cardRepository;


    @Override
    public TransactionResponse create(TransactionRequest request) {
        Payment payment = paymentRepository.findById(request.paymentId())
                .orElseThrow(() -> new NoSuchElementException("Payment Not Found"));
        PaymentCard card = cardRepository.findById(request.cardId())
                .orElseThrow(() -> new NoSuchElementException("Card Not Found"));

        Transaction transaction = mapper.toEntity(request);
        transaction.setPayment(payment);
        transaction.setCard(card);
        transaction.setBankTransactionId(UUID.randomUUID().toString());
        Transaction saved = transactionRepository.save(transaction);
        return mapper.toDto(saved);
    }

    @Override
    public TransactionResponse getById(UUID id) {
        return transactionRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new NoSuchElementException("Transaction Not Found"));
    }

    @Override
    public List<TransactionResponse> getAll() {
        return transactionRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public TransactionResponse updateStatus(UUID id, String status) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Transaction Not Found"));
        transaction.setStatus(TransactionStatus.valueOf(status.toUpperCase()));
        return mapper.toDto(transactionRepository.save(transaction));
    }

    @Override
    public void delete(UUID id) {
        transactionRepository.deleteById(id);
    }
}
