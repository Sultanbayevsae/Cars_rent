package org.example.server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.example.server.dto.AuthResponse;
import org.example.server.dto.LoginDto;
import org.example.server.dto.RegisterDto;
import org.example.server.exception.ErrorDTO;
import org.example.server.service.AuthService;
import org.example.server.utill.AppConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(AppConstants.BASE_URL + AppConstants.VERSION + "/auth")
@Tag(name = "Authentication controller", description = "REST API CRUD operations for authenticating and authorizing users")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Operation(
            description = "Executed when user logs in",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Logged In"
                    ),
                    @ApiResponse(
                            responseCode = "407",
                            description = "Error while logging in",
                            content = @Content(schema = @Schema(implementation = BadCredentialsException.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal error",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))
                    )
            }
    )
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginDto loginDto) throws BadRequestException {
        AuthResponse login = authService.login(loginDto);
        return ResponseEntity.ok(login);
    }

    @PostMapping("/register")
    @Operation(
            description = "Operates when registering",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Occurs when provided credentials are invalid",
                            content = @Content(schema = @Schema(implementation = BadRequestException.class))
                    )
            }
    )
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterDto registerDto) throws BadRequestException {
        AuthResponse register = authService.register(registerDto);
        return ResponseEntity.ok(register);
    }

    @PostMapping("/logout")
    @Operation(
            description = "Logging out endpoint",
            responses = {
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Provided credentials are invalid",
                            content = @Content(schema = @Schema(implementation = BadRequestException.class))
                    )
            }
    )
    public ResponseEntity<String> logout(@Valid @RequestBody String token) throws BadRequestException {
        authService.logout(token);
        return ResponseEntity.ok("User successfully logged out");
    }
}
