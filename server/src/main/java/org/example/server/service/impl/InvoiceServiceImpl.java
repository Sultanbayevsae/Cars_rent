package org.example.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.server.dto.InvoiceRequestDTO;
import org.example.server.entity.Invoice;
import org.example.server.entity.InvoiceStatus;
import org.example.server.entity.Rental;
import org.example.server.entity.User;
import org.example.server.mapper.InvoiceMapper;
import org.example.server.repository.InvoiceRepository;
import org.example.server.repository.RentalRepository;
import org.example.server.repository.UserRepository;
import org.example.server.service.InvoiceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final InvoiceMapper invoiceMapper;

    @Override
    @Transactional
    public Invoice createInvoice(InvoiceRequestDTO dto) {
        Rental rental = rentalRepository.findById(dto.rentalId())
                .orElseThrow(() -> new RuntimeException("rental not found"));
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("user not found"));

        Invoice invoice = invoiceMapper.toEntity(dto, rental, user);
        invoice.setInvoiceNumber("INV-" + UUID.randomUUID());
        invoice.setStatus(InvoiceStatus.PENDING);
        invoice.setCreatedAt(LocalDateTime.now());
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice getInvoiceById(UUID id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice Not Found"));
    }

    @Override
    public List<Invoice> getInvoiceByUserId(UUID userId) {
        return invoiceRepository.findByUserId(userId);
    }

    @Override
    public Invoice markAsPaid(UUID id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice Not Found"));
        invoice.setStatus(InvoiceStatus.PAID);
        invoice.setPaidAt(LocalDateTime.now());
        return invoiceRepository.save(invoice);
    }
}
