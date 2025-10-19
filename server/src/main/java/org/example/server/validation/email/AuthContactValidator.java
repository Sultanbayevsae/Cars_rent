package org.example.server.validation.email;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.server.dto.RegisterDto;

public class AuthContactValidator implements ConstraintValidator<ValidAuthContact, RegisterDto> {
    @Override
    public boolean isValid(RegisterDto dto, ConstraintValidatorContext constraintValidatorContext) {
        return (dto.email() != null && !dto.email().isBlank() ||
                (dto.phoneNumber() != null && !dto.phoneNumber().isBlank()));
    }
}
