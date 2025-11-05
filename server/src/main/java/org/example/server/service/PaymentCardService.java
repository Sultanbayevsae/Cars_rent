package org.example.server.service;

import org.example.server.dto.PaymentCardRequestDTO;
import org.example.server.dto.PaymentCardResponseDTO;
import org.example.server.entity.User;

import java.util.List;
import java.util.UUID;

public interface PaymentCardService {

    PaymentCardResponseDTO createCard(PaymentCardRequestDTO dto);

    PaymentCardResponseDTO getCardById(UUID id);

    List<PaymentCardResponseDTO> getCardsByUserId(UUID userId);

    PaymentCardResponseDTO deactivateCard(UUID id);
}
