package org.example.server.validation.branch;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.server.repository.BranchRepository;

import java.util.UUID;

public class ValidBranchIdHandler implements ConstraintValidator<ValidBranchId, UUID> {
    private final BranchRepository repository;

    public ValidBranchIdHandler(BranchRepository repository) {
        this.repository = repository;
    }


    @Override
    public boolean isValid(UUID uuid, ConstraintValidatorContext context) {
        if (repository.existsById(uuid)) {
            return true;
        } else {
            String MESSAGE = "INVALID_BRANCH_ID: Branch with ID " + uuid + " does not exist";
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(MESSAGE)
                    .addConstraintViolation();
            return false;
        }
    }
}
