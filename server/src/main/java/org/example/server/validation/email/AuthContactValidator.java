package org.example.server.validation.email;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.server.dto.RegisterDto;

public class AuthContactValidator implements ConstraintValidator<ValidAuthContact, RegisterDto> {

    @Override
    public boolean isValid(RegisterDto dto, ConstraintValidatorContext context) {
        boolean emailValid = dto.email() != null && !dto.email().isBlank();
        boolean phoneValid = dto.phoneNumber() != null && !dto.phoneNumber().isBlank();
        if(emailValid || phoneValid) return true;
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
                "Either email or phone number must be provided"
        ).addConstraintViolation();
        return false;
    }
}
