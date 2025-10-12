package org.example.server.validation.address;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.server.repository.AddressRepository;

import java.util.UUID;

public class ValidAddressIdHandler implements ConstraintValidator<ValidAddressId, UUID> {
    private final AddressRepository repository;

    public ValidAddressIdHandler(AddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(UUID uuid, ConstraintValidatorContext context) {
        if (repository.existsById(uuid)) {
            return true;
        } else {
            String MESSAGE = "INVALID_ADDRESS_ID: Address with ID " + uuid + " does not exist";
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(MESSAGE)
                    .addConstraintViolation();
            return false;
        }
    }
}
