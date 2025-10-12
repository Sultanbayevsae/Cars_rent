package org.example.server.validation.address;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.UUID;

@Constraint(validatedBy = ValidAddressIdHandler.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAddressId {
    String value() default "";
    String message() default "";
    Class<? extends UUID> id() default UUID.class;
}

