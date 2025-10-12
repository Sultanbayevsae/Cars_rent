package org.example.server.validation.region;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.server.validation.region.handler.ValidRegionCreatorHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidRegionCreatorHandler.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRegionCreator {
    String message() default "INVALID_REGION_CREATOR. region creator is not valid.";

    Class<?>[] groups
            () default {};

    Class<? extends Payload>[] payload() default {};
}

