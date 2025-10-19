package org.example.server.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

        @Email(message = "Email should be valid")
        @NotBlank(message = "Email is required")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least {min} characters")
        String password,

        @NotBlank(message = "Phone number is required")
        @Pattern(regexp = "\\+998", message = "Phone number must be valid, e.g., +998901234567")
        String phoneNumber
) {
}
