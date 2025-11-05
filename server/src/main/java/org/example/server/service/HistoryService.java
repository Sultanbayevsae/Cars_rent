package org.example.server.service;

import org.example.server.dto.HistoryRequestDTO;
import org.example.server.dto.HistoryResponseDTO;

import java.util.List;
import java.util.UUID;

public interface HistoryService {

    HistoryResponseDTO createHistory(HistoryRequestDTO dto);

    List<HistoryResponseDTO> getUserHistory(UUID userId);
}
