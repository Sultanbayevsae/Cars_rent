package org.example.server.service;

import org.example.server.dto.RentalDTO;

import java.util.List;
import java.util.UUID;

public interface RentalService {
    RentalDTO createRental(RentalDTO dto);
    RentalDTO getRental(Long id);
    List<RentalDTO> getRentalsByUser(UUID userId);
}