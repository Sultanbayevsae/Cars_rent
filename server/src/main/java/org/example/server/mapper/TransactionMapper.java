package org.example.server.mapper;

import org.example.server.dto.TransactionRequest;
import org.example.server.dto.TransactionResponse;
import org.example.server.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", constant = "PENDING")
    @Mapping(target = "bankTransactionId", ignore = true)
    @Mapping(target = "payment", ignore = true)
    @Mapping(target = "card", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    Transaction toEntity(TransactionRequest dto);

    @Mapping(source = "payment.id", target = "paymentId")
    @Mapping(source = "card.id", target = "cardId")
    TransactionResponse toDto(Transaction entity);
}
