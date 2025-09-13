package com.internshiptask.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateRangeValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)

public @interface ValidDateRange {
     String message() default "endDate must be after startDate";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
