package com.danbro.service.common.validtors.anotations;

import com.danbro.service.common.validtors.IsBoolConstraintValidator;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Danrbo
 * @Classname IsPositive
 * @Description TODO 校验参数是否为正数
 * @Date 2021/2/4 14:54
 */
@Documented
@Constraint(validatedBy = {IsBoolConstraintValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface IsPositive {
}
