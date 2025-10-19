package org.example.server.validation.email;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AuthContactValidator.class)
@Documented
public @interface ValidAuthContact {
    String message() default "Either email or phone number must be provided";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
