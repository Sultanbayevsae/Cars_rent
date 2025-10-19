package org.example.server.validation.branch;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.UUID;

@Constraint(validatedBy = ValidBranchIdHandler.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBranchId {
    String value() default "";
    String message() default "";
    Class<? extends UUID> id() default UUID.class;
}
