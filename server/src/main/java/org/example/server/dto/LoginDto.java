package org.example.server.dto;

import jakarta.validation.constraints.NotBlank;
import org.example.server.validation.email.ValidAuthContact;

@ValidAuthContact
public record LoginDto(
        @NotBlank String emailOrPhone,
        @NotBlank String password
) {
}
