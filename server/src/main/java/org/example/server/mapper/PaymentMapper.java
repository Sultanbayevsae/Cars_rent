package org.example.server.mapper;

import org.example.server.dto.PaymentRequestDTO;
import org.example.server.dto.PaymentResponseDTO;
import org.example.server.entity.Payment;
import org.example.server.entity.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    @Mapping(source = "rental", target = "rental")
    Payment toEntity(PaymentRequestDTO dto, Rental rental);

    @Mapping(source = "rental.id", target = "rentalId")
    PaymentResponseDTO toResponse(Payment payment);
}
