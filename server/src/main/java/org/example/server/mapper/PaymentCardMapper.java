package org.example.server.mapper;

import org.example.server.dto.PaymentCardRequestDTO;
import org.example.server.dto.PaymentCardResponseDTO;
import org.example.server.entity.PaymentCard;
import org.example.server.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentCardMapper {

    @Mapping(source = "dto.cardNumberMasked", target = "cardNumberMasked")
    @Mapping(source = "dto.cardHolderName", target = "cardHolderName")
    @Mapping(source = "dto.expiryDate", target = "expiryDate")
    @Mapping(source = "user", target = "user")
    PaymentCard toEntity(PaymentCardRequestDTO dto, User user);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "active", target = "isActive")
    PaymentCardResponseDTO toResponse(PaymentCard card);
}