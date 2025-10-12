package org.example.server.validation.region.handler;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.server.repository.RegionRepository;
import org.example.server.validation.region.ValidRegionId;

public class ValidRegionIdHandler implements ConstraintValidator<ValidRegionId, Long> {
    private final RegionRepository regionRepository;

    public ValidRegionIdHandler(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public boolean isValid(Long regionId, ConstraintValidatorContext constraintValidatorContext) {
        if (regionRepository.existsById(regionId)) {
            return true;
        } else {
            String MESSAGE = "INVALID_REGION_ID: Siz yaroqsiz regionId: " + regionId + " jo`natdingiz.";
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate(MESSAGE)
                    .addConstraintViolation();
            return false;
        }
    }
}