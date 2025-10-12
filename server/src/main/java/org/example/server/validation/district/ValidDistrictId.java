package org.example.server.validation.district;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidDistrictIdHandler.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDistrictId {

    String message() default "INVALID_DISTRICT_ID. district id is not valid.";

    Class<?>[] groups
            () default {};

    Class<? extends Payload>[] payload() default {};

}