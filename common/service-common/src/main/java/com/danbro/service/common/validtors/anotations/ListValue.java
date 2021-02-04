package com.danbro.service.common.validtors.anotations;

import com.danbro.service.common.validtors.ListValueConstraintValidator;

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
 * @Classname ListValue
 * @Description TODO 校验参数是否为指定的值
 * @Date 2021/2/4 12:02
 */
@Documented
@Constraint(validatedBy = {ListValueConstraintValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface ListValue {
    String message() default "{com.danbro.validation.constraints.ListValue.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int[] values() default {};
}
