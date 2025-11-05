package org.example.server.mapper;

import org.example.server.dto.InvoiceRequestDTO;
import org.example.server.dto.InvoiceResponseDTO;
import org.example.server.entity.Invoice;
import org.example.server.entity.InvoiceStatus;
import org.example.server.entity.Rental;
import org.example.server.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    default Invoice toEntity(InvoiceRequestDTO dto, Rental rental, User user) {
        Invoice invoice = new Invoice();
        invoice.setRental(rental);
        invoice.setUser(user);
        invoice.setAmount(dto.amount());
        invoice.setCurrency("UZS");
        invoice.setStatus(InvoiceStatus.PENDING);
        return invoice;
    }

    default InvoiceResponseDTO toResponse(Invoice invoice) {
        return new InvoiceResponseDTO(
                invoice.getId(),
                invoice.getInvoiceNumber(),
                invoice.getAmount(),
                invoice.getCurrency(),
                invoice.getStatus().toString(),
                invoice.getRental().getId(),
                invoice.getUser().getId(),
                invoice.getCreatedAt(),
                invoice.getPaidAt()
        );
    }
}
