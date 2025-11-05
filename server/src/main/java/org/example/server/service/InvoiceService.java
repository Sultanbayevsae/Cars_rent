package org.example.server.service;

import org.example.server.dto.InvoiceRequestDTO;
import org.example.server.entity.Invoice;

import java.util.List;
import java.util.UUID;

public interface InvoiceService {
    Invoice createInvoice(InvoiceRequestDTO dto);

    Invoice getInvoiceById(UUID id);

    List<Invoice> getInvoiceByUserId(UUID userId);

    Invoice markAsPaid(UUID id);
}
