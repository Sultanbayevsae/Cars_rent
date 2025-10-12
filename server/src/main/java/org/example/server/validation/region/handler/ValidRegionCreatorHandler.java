package org.example.server.validation.region.handler;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.server.dto.RegionCreator;
import org.example.server.repository.RegionRepository;
import org.example.server.validation.region.ValidRegionCreator;

public class ValidRegionCreatorHandler implements ConstraintValidator<ValidRegionCreator, RegionCreator> {
    private final RegionRepository regionRepository;

    public ValidRegionCreatorHandler(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public boolean isValid(RegionCreator creator, ConstraintValidatorContext constraintValidatorContext) {
        if (!regionRepository.existsRegionByName(creator.name())) {
            return true;
        } else {
            String MESSAGE = "INVALID_REGION_CREATOR: region name already exists with: " + creator.name();
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate(MESSAGE)
                    .addConstraintViolation();
            return false;
        }
    }
}