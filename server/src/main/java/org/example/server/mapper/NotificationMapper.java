package org.example.server.mapper;

import org.example.server.dto.NotificationRequestDTO;
import org.example.server.dto.NotificationResponseDTO;
import org.example.server.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface NotificationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "dto.title", target = "title")
    @Mapping(source = "dto.message", target = "message")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "car", target = "car")
    @Mapping(source = "transaction", target = "transaction")
    @Mapping(source = "transaction.type", target = "type", qualifiedByName = "mapTransactionTypeToNotificationType")
    @Mapping(target = "read", constant = "false")
    @Mapping(target = "createdAt", ignore = true)
    Notification toEntity(NotificationRequestDTO dto, User user, Car car, Transaction transaction);

    @Named("mapTransactionTypeToNotificationType")
    default NotificationType mapTransactionTypeToNotificationType(TransactionType transactionType) {
        if (transactionType == null) return NotificationType.SYSTEM_ALERT;
        return switch (transactionType) {
            case PAYMENT -> NotificationType.PAYMENT_SUCCESS;
            case REFUND -> NotificationType.REFUND;
        };
    }

    @Mapping(source = "id", target = "id")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "car.id", target = "carId")
    @Mapping(source = "transaction.id", target = "transactionId")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "message", target = "message")
    @Mapping(source = "read", target = "read")
    NotificationResponseDTO toResponse(Notification notification);
}
