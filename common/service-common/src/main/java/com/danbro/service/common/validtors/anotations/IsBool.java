package com.danbro.service.common.validtors.anotations;

import com.danbro.service.common.validtors.IsBoolConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Danrbo
 * @Classname IsBoolean
 * @Description TODO
 * @Date 2021/2/4 12:18
 */
@Documented
@Constraint(validatedBy = {IsBoolConstraintValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface IsBool {
    String message() default "{com.danbro.validation.constraints.IsBool.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
