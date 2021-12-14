package com.auth.server.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static  java.lang.annotation.ElementType.*;

@Target(value = { METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ConstantValidatorImpl.class})
@Repeatable(ConstantValidation.List.class)
public @interface ConstantValidation {

    Constants[] allowedConstants() default  {};
    String message() default "Constant Validation Failed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        ConstantValidation[] value();
    }
}
