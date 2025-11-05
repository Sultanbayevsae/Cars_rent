package org.example.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.server.dto.PaymentCardRequestDTO;
import org.example.server.dto.PaymentCardResponseDTO;
import org.example.server.entity.PaymentCard;
import org.example.server.entity.User;
import org.example.server.mapper.PaymentCardMapper;
import org.example.server.repository.PaymentCardRepository;
import org.example.server.repository.UserRepository;
import org.example.server.service.PaymentCardService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentCardServiceImpl implements PaymentCardService {

    private final PaymentCardRepository paymentCardRepository;
    private final UserRepository userRepository;
    private final PaymentCardMapper mapper;

    @Override
    public PaymentCardResponseDTO createCard(PaymentCardRequestDTO dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        PaymentCard card = mapper.toEntity(dto, user);
        card.setToken(UUID.randomUUID().toString());
        PaymentCard saved = paymentCardRepository.save(card);
        return mapper.toResponse(saved);
    }

    @Override
    public PaymentCardResponseDTO getCardById(UUID id) {
        return paymentCardRepository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Card Not Found"));
    }

    @Override
    public List<PaymentCardResponseDTO> getCardsByUserId(UUID userId) {
        return paymentCardRepository.findByUserId(userId)
                .stream().map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentCardResponseDTO deactivateCard(UUID id) {
        PaymentCard card = paymentCardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card Not Found"));
        card.setActive(false);
        return mapper.toResponse(paymentCardRepository.save(card));
    }
}
