package com.danbro.service.common.validtors.anotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


import javax.validation.Constraint;
import javax.validation.Payload;

import com.danbro.service.common.validtors.NumberConstraintValidator;
import com.danbro.service.common.validtors.groups.Insert;
import com.danbro.service.common.validtors.groups.Select;
import com.danbro.service.common.validtors.groups.Update;


import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {NumberConstraintValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface isNumber {
    boolean required() default true;

    String message() default "{com.danbro.validation.constraints.IsNumber.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
