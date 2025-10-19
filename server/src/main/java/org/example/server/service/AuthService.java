package org.example.server.service;

import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.example.server.dto.AuthResponse;
import org.example.server.dto.LoginDto;
import org.example.server.dto.RegisterDto;

public interface AuthService {
    AuthResponse login(@Valid LoginDto dto) throws BadRequestException;
    AuthResponse register(@Valid RegisterDto dto) throws BadRequestException;
    void logout(String token) throws BadRequestException;

}
