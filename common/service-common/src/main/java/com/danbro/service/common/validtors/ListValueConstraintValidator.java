package com.danbro.service.common.validtors;

import com.danbro.service.common.validtors.anotations.ListValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Danrbo
 * @Classname ListValueConstraintValidtors
 * @Description TODO 参数是否在指定范围内的校验器
 * @Date 2021/2/4 12:13
 */
public class ListValueConstraintValidator implements ConstraintValidator<ListValue, Integer> {
    HashSet<Integer> set = new HashSet<>();

    @Override
    public void initialize(ListValue constraintAnnotation) {
        int[] values = constraintAnnotation.values();
        Arrays.stream(values).forEach(set::add);
    }

    @Override
    public boolean isValid(Integer num, ConstraintValidatorContext constraintValidatorContext) {
        return set.contains(num);
    }
}
