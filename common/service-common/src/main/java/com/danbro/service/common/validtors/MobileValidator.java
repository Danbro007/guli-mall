package com.danbro.service.common.validtors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.alibaba.nacos.client.utils.ValidatorUtils;
import com.danbro.common.utils.MyValidatorUtils;
import com.danbro.service.common.validtors.anotations.IsMobile;

/**
 * @Classname MobileValidator
 * @Description TODO 手机号检验器
 * @Date 2021/1/5 15:22
 * @Author Danrbo
 */
public class MobileValidator implements ConstraintValidator<IsMobile, String> {

    private boolean required = false;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    /**
     * 校验规则
     *
     * @param value   手机号
     * @param context 校验上下文
     * @return 校验结果
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // value就是要校验的数据了
        if (value != null && required) {
            return MyValidatorUtils.isMobile(value);
        }
        return false;
    }
}
