package org.example.server.service;

import org.example.server.dto.PaymentRequestDTO;
import org.example.server.dto.PaymentResponseDTO;
import org.example.server.entity.Payment;
import org.example.server.entity.PaymentMethod;
import org.example.server.entity.PaymentStatus;
import org.example.server.entity.Rental;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface PaymentService {
    PaymentResponseDTO createPayment(PaymentRequestDTO dto);

    PaymentResponseDTO findPaymentById(Long id);

    List<PaymentResponseDTO> getPaymentsByUserId(UUID id);

    PaymentResponseDTO updatePaymentStatus(Long id, PaymentStatus paymentStatus);

}
