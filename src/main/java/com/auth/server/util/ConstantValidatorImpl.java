package com.auth.server.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class ConstantValidatorImpl implements ConstraintValidator<ConstantValidation, String> {

    private Constants[] allowedConstants;

    @Override
    public void initialize(ConstantValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        allowedConstants = constraintAnnotation.allowedConstants();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Set<String> allowedValues = Arrays.stream(allowedConstants).map(constants -> constants.getValue()).collect(Collectors.toSet());
        return allowedValues.contains(value);
    }
}
