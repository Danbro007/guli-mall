package com.danbro.service.common.validtors.anotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import com.danbro.service.common.validtors.MobileValidator;
import com.danbro.service.common.validtors.groups.Insert;
import com.danbro.service.common.validtors.groups.Select;
import com.danbro.service.common.validtors.groups.Update;


/**
 * @Classname IsMobile
 * @Description TODO 手机号校验器注解
 * @Date 2021/1/5 15:23
 * @Author Danrbo
 */

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {MobileValidator.class})
public @interface IsMobile {
    boolean required() default true;

    String message() default "{com.danbro.validation.constraints.IsMobile.message}";

    Class<?>[] groups() default {Insert.class, Update.class, Select.class};

    Class<? extends Payload>[] payload() default {};
}
