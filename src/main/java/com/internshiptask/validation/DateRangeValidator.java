package com.internshiptask.validation;

import com.internshiptask.dto.TripRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, TripRequest> {
    @Override
    public boolean isValid(TripRequest value, ConstraintValidatorContext context) {
        if (value == null) return true;
        LocalDate start = value.getStartDate();
        LocalDate end = value.getEndDate();
        if (start == null || end == null) return true; // individual @NotNull will handle
        return end.isAfter(start); // strictly after; change if you want to allow same day
    }
}
