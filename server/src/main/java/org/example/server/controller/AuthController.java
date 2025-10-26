// ============================================================================
// 10. NEW: AuthController.java
// Location: src/main/java/org/example/server/controller/AuthController.java
// ============================================================================
        package org.example.server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.server.dto.*;
import org.example.server.service.AuthService;
import org.example.server.utill.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstants.BASE_URL + "/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "User authentication endpoints")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "Register new user")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody RegisterDto dto) {
        ApiResponse response = authService.register(dto);
        return response.getSuccess()
                ? ResponseEntity.status(HttpStatus.CREATED).body(response)
                : ResponseEntity.badRequest().body(response);
    }

    @GetMapping("/verify")
    @Operation(summary = "Verify email/phone with token")
    public ResponseEntity<ApiResponse> verify(@RequestParam String token) {
        boolean verified = authService.verifyEmail(token);
        if (verified) {
            return ResponseEntity.ok(new ApiResponse(true, "Account verified successfully"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse(false, "Invalid or expired token"));
    }

    @DeleteMapping("/ignore-verification")
    @Operation(summary = "Delete unverified account")
    public ResponseEntity<ApiResponse> ignoreVerification(@RequestParam String token) {
        boolean deleted = authService.ignoreVerification(token);
        if (deleted) {
            return ResponseEntity.ok(new ApiResponse(true, "Account deleted successfully"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse(false, "Invalid token"));
    }

    @PostMapping("/login")
    @Operation(summary = "Login user")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginDto dto) {
        AuthResponse response = authService.login(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    @Operation(summary = "Refresh access token")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshTokenRequest request) {
        AuthResponse response = authService.refresh(request.refreshToken());
        return ResponseEntity.ok(response);
    }

    public record RefreshTokenRequest(String refreshToken) {}
}