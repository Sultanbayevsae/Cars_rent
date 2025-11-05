package org.example.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.server.dto.HistoryRequestDTO;
import org.example.server.dto.HistoryResponseDTO;
import org.example.server.entity.Car;
import org.example.server.entity.History;
import org.example.server.entity.User;
import org.example.server.mapper.HistoryMapper;
import org.example.server.repository.CarRepository;
import org.example.server.repository.HistoryRepository;
import org.example.server.repository.UserRepository;
import org.example.server.service.HistoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final HistoryMapper historyMapper;

    @Override
    public HistoryResponseDTO createHistory(HistoryRequestDTO dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Car car = carRepository.findById(dto.carId())
                .orElseThrow(() -> new RuntimeException("Car not found"));

        History history = historyMapper.toEntity(dto, user, car);
        return historyMapper.toResponse(historyRepository.save(history));
    }

    @Override
    public List<HistoryResponseDTO> getUserHistory(UUID userId) {
        return historyRepository.findByUserId(userId).stream()
                .map(historyMapper::toResponse)
                .collect(Collectors.toList());
    }
}
