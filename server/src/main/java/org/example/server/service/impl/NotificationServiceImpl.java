package org.example.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.server.dto.NotificationRequestDTO;
import org.example.server.dto.NotificationResponseDTO;
import org.example.server.entity.Car;
import org.example.server.entity.Notification;
import org.example.server.entity.Transaction;
import org.example.server.entity.User;
import org.example.server.mapper.NotificationMapper;
import org.example.server.repository.CarRepository;
import org.example.server.repository.NotificationRepository;
import org.example.server.repository.TransactionRepository;
import org.example.server.repository.UserRepository;
import org.example.server.service.NotificationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final TransactionRepository transactionRepository;
    private final NotificationMapper notificationMapper;

    @Override
    public NotificationResponseDTO createNotification(NotificationRequestDTO dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Car car = carRepository.findById(dto.carId())
                .orElseThrow(() -> new RuntimeException("Car not found"));
        Transaction transaction = transactionRepository.findById(dto.transactionId())
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        Notification notification = notificationMapper.toEntity(dto, user, car, transaction);
        return notificationMapper.toResponse(notificationRepository.save(notification));
    }

    @Override
    public List<NotificationResponseDTO> getUserNotifications(UUID userId) {
        return notificationRepository.findByUserId(userId).stream()
                .map(notificationMapper::toResponse)
                .collect(Collectors.toList());
    }
}
