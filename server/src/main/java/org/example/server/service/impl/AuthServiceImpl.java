package org.example.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.server.dto.AuthResponse;
import org.example.server.dto.LoginDto;
import org.example.server.dto.RegisterDto;
import org.example.server.entity.Role;
import org.example.server.entity.User;
import org.example.server.repository.RoleRepository;
import org.example.server.repository.UserRepository;
import org.example.server.secret.JwtProvider;
import org.example.server.service.AuthService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.coyote.BadRequestException;

import java.util.Set;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse register(RegisterDto dto) throws BadRequestException {
        if(userRepository.existsByEmail(dto.email())) {
            throw new BadRequestException("Email already registered");
        }
        if(userRepository.existsByPhoneNumber(dto.phoneNumber())) {
            throw new BadRequestException("Phone number already registered");
        }

        Role defaultRole = roleRepository.findByRoleName("USER")
                .orElseThrow(() -> new BadRequestException("Default role USER not found"));

        User user = User.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .phoneNumber(dto.phoneNumber())
                .password(passwordEncoder.encode(dto.password()))
                .roles(Set.of(defaultRole))
                .enabled(true)
                .build();

        userRepository.save(user);

        String token = jwtProvider.generateToken(user);

        return new AuthResponse(token);
    }

    @Override
    public AuthResponse login(LoginDto dto) throws BadRequestException {
        User user = userRepository.findUserByEmail(dto.emailOrPhone())
                .orElseGet(() -> userRepository.findUserByPhoneNumber(dto.emailOrPhone())
                        .orElseThrow(() -> new BadCredentialsException("User not found")));

        if(!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        if(!user.isEnabled()) {
            throw new BadCredentialsException("User account not enabled");
        }

        String token = jwtProvider.generateToken(user);
        return new AuthResponse(token);
    }

    @Override
    public void logout(String token) throws BadRequestException {
        System.out.println("User logged out. Token: " + token);
    }
}
