package org.example.server.service;

import org.example.server.dto.ApiResponse;
import org.example.server.dto.AuthResponse;
import org.example.server.dto.LoginDto;
import org.example.server.dto.RegisterDto;

import java.util.UUID;

public interface AuthService {
    ApiResponse register(RegisterDto dto);
    boolean verifyEmail(String token);
    boolean ignoreVerification(String token);
    AuthResponse login(LoginDto dto);
    AuthResponse refresh(String refreshToken);
    void logout(UUID userId);
    ApiResponse delete(UUID userId);
}
