package org.example.server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.server.validation.email.ValidAuthContact;

@ValidAuthContact
public record RegisterDto(
        @NotBlank(message = "First name is required")
        @Size(min = 1, message = "First name should be at least {min} letters long")
        String firstName,

        @NotBlank(message = "Last name is required")
        @Size(min = 1, message = "Last name should be at least {min} letters long")
        String lastName,

        String email,
        String phoneNumber,

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least {min} characters")
        String password
) {}
