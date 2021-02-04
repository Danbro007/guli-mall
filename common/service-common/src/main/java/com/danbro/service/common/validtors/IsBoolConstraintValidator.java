package com.danbro.service.common.validtors;

import com.danbro.service.common.validtors.anotations.IsBool;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;

/**
 * @author Danrbo
 * @Classname IsBoolConstraintValidators
 * @Description TODO 校验是否为布尔值
 * @Date 2021/2/4 12:19
 */

public class IsBoolConstraintValidator implements ConstraintValidator<IsBool, Boolean> {
    HashSet<Boolean> set = new HashSet<>(2);


    @Override
    public void initialize(IsBool constraintAnnotation) {
        set.add(true);
        set.add(false);
    }

    @Override
    public boolean isValid(Boolean aBoolean, ConstraintValidatorContext constraintValidatorContext) {
        return set.contains(aBoolean);
    }
}
