package org.example.server.repository;

import org.example.server.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {
    List<Invoice> findByUserId(UUID userId);
    List<Invoice> findByRentalId(Long rentalId);
}
