package org.example.server.validation.region;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.server.validation.region.handler.ValidRegionIdHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidRegionIdHandler.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRegionId {
    String message() default "INVALID_REGION_ID. region id is not valid.";

    Class<?>[] groups
            () default {};

    Class<? extends Payload>[] payload() default {};
}

