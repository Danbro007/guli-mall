package com.danbro.service.common.validtors;

import java.util.Date;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.danbro.common.utils.MyValidatorUtils;
import com.danbro.service.common.validtors.anotations.IsBirthday;

/**
 * @Classname DateConstraintValidator
 * @Description TODO 生日校验器
 * @Date 2021/2/11 12:17
 * @Created by Administrator
 */
public class BirthdayConstraintValidator implements ConstraintValidator<IsBirthday, Date> {
    private boolean required = false;

    @Override
    public void initialize(IsBirthday constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        if (date != null && required) {
            return MyValidatorUtils.isBirthday(date.toString());
        }
        return false;
    }
}
