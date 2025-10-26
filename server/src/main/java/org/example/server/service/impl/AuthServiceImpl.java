// ============================================================================
// 8. FIXED: AuthServiceImpl.java
// Location: src/main/java/org/example/server/service/impl/AuthServiceImpl.java
// ============================================================================
package org.example.server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.server.dto.*;
import org.example.server.entity.Role;
import org.example.server.entity.User;
import org.example.server.mapper.UserMapper;
import org.example.server.repository.RoleRepository;
import org.example.server.repository.UserRepository;
import org.example.server.security.JwtUtil;
import org.example.server.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public ApiResponse register(RegisterDto dto) {
        if (dto.email() != null && !dto.email().isBlank()) {
            if (userRepository.existsByEmail(dto.email())) {
                return new ApiResponse(false, "Email already in use");
            }
        }else {
            return new ApiResponse(false, "Not provided credentials");
        }
        if (dto.phoneNumber() != null && !dto.phoneNumber().isBlank()) {
            if (userRepository.existsByPhoneNumber(dto.phoneNumber())) {
                return new ApiResponse(false, "Phone number already in use");
            }
        }

        User user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setEnabled(false);

        Role userRole = roleRepository.findByRoleCode("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);

        String accessToken = jwtUtil.generateToken(user);
        user.setVerifyToken(accessToken);
        user.setExpiryDate(LocalDateTime.now().plusHours(24));

        userRepository.save(user);

        log.info("User registered successfully: {}", dto.email());

        // TODO: Email/SMS yuborish logikasi
        return new ApiResponse(true, "User registered successfully. Check your email/phone for verification.", accessToken);
    }

    @Override
    @Transactional
    public boolean verifyEmail(String token) {
        try {
            String username = jwtUtil.extractUsername(token);
            User user = userRepository.findByEmail(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if (!jwtUtil.validateToken(token, user)) {
                log.warn("Invalid JWT token for user: {}", username);
                return false;
            }

            user.setEnabled(true);
            user.setVerifyToken(null);
            user.setExpiryDate(null);
            userRepository.save(user);

            log.info("User verified successfully: {}", user.getEmail());
            return true;

        } catch (Exception e) {
            log.error("Email verification failed: {}", e.getMessage());
            return false;
        }
    }

    @Override
    @Transactional
    public boolean ignoreVerification(String token) {
        User user = userRepository.findByVerifyToken(token).orElse(null);
        if (user == null) {
            return false;
        }

        userRepository.delete(user);
        log.info("User deleted (verification ignored): {}", user.getEmail());
        return true;
    }

    @Override
    public AuthResponse login(LoginDto dto) {
        User user = userRepository.findByEmailOrPhoneNumber(dto.emailOrPhone())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.isEnabled()) {
            throw new RuntimeException("Account not verified. Please check your email/phone.");
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(), // Spring Security username (email)
                            dto.password()
                    )
            );
        } catch (AuthenticationException e) {
            log.error("Authentication failed for user: {}", dto.emailOrPhone());
            throw new RuntimeException("Invalid credentials");
        }

        String accessToken = jwtUtil.generateToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(user);

        log.info("User logged in successfully: {}", user.getEmail());

        AuthResponse response = new AuthResponse(accessToken);
        response.setRefreshToken(refreshToken); // Yangi field qo'shish kerak AuthResponse'ga
        return response;
    }

    @Override
    public AuthResponse refresh(String refreshToken) {
        try {
            String username = jwtUtil.extractUsernameFromRefreshToken(refreshToken);
            User user = userRepository.findByEmail(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if (!jwtUtil.validateToken(refreshToken, user)) {
                throw new RuntimeException("Invalid refresh token");
            }

            String newAccessToken = jwtUtil.generateToken(user);
            String newRefreshToken = jwtUtil.generateRefreshToken(user);

            AuthResponse response = new AuthResponse(newAccessToken);
            response.setRefreshToken(newRefreshToken);
            return response;
        } catch (Exception e) {
            log.error("Refresh token validation failed: {}", e.getMessage());
            throw new RuntimeException("Invalid refresh token");
        }
    }

    @Override
    public void logout(UUID userId) {
        log.info("User logged out: {}", userId);
    }
}
