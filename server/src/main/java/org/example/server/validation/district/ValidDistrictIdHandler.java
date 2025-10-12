package org.example.server.validation.district;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.server.repository.DistrictRepository;

import java.util.UUID;

public class ValidDistrictIdHandler implements ConstraintValidator<ValidDistrictId, UUID> {
    private final DistrictRepository districtRepository;

    public ValidDistrictIdHandler(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }


    @Override
    public boolean isValid(UUID districtId, ConstraintValidatorContext constraintValidatorContext) {
        if (!districtRepository.existsById(districtId)) {
            return true;
        } else {
            String MESSAGE = "INVALID_DISTRICT_ID: Siz yaroqsiz districtId: " + districtId + " jo`natdingiz.";
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate(MESSAGE)
                    .addConstraintViolation();
            return false;
        }
    }
}