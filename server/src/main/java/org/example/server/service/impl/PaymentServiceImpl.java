package org.example.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.server.dto.PaymentRequestDTO;
import org.example.server.dto.PaymentResponseDTO;
import org.example.server.entity.Payment;
import org.example.server.entity.PaymentStatus;
import org.example.server.entity.Rental;
import org.example.server.mapper.PaymentMapper;
import org.example.server.repository.PaymentRepository;
import org.example.server.repository.RentalRepository;
import org.example.server.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final RentalRepository rentalRepository;
    private final PaymentMapper mapper;

    @Override
    public PaymentResponseDTO createPayment(PaymentRequestDTO dto) {
        Rental rental = rentalRepository.findById(dto.rentalId())
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        Payment payment = mapper.toEntity(dto, rental);
        payment = paymentRepository.save(payment);

        return mapper.toResponse(payment);
    }

    @Override
    public PaymentResponseDTO findPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        return mapper.toResponse(payment);
    }

    @Override
    public List<PaymentResponseDTO> getPaymentsByUserId(UUID userId) {
        return paymentRepository.findByRentalUserId(userId)
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentResponseDTO updatePaymentStatus(Long id, PaymentStatus status) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        payment.setStatus(status);
        payment = paymentRepository.save(payment);

        return mapper.toResponse(payment);
    }
}
