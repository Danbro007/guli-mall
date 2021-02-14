package com.danbro.service.common.validtors;

import java.util.Date;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.danbro.common.utils.MyValidatorUtils;
import com.danbro.service.common.validtors.anotations.IsBirthday;
import com.danbro.service.common.validtors.anotations.isNumber;

/**
 * @Classname DateConstraintValidator
 * @Description TODO 数字校验器
 * @Date 2021/2/11 12:17
 * @Created by Administrator
 */
public class NumberConstraintValidator implements ConstraintValidator<isNumber, String> {
    private boolean required = false;

    @Override
    public void initialize(isNumber constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String number, ConstraintValidatorContext constraintValidatorContext) {
        if (number != null && required) {
            return MyValidatorUtils.isNumber(number);
        }
        return false;
    }
}
