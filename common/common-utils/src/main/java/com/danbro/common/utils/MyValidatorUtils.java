package com.danbro.common.utils;

import cn.hutool.core.lang.Validator;

/**
 * @Classname MyValidatorUtils
 * @Description TODO 字段校验工具类
 * @Date 2021/2/11 12:06
 * @Created by Administrator
 */
public class MyValidatorUtils {

    /**
     * 判断字符串是不是手机号
     *
     * @param mobile 要校验的手机号
     * @return 校验结果
     */
    public static Boolean isMobile(String mobile) {
        return Validator.isMobile(mobile);
    }


    public static Boolean isBirthday(String birthday) {
        return Validator.isBirthday(birthday);
    }


    public static Boolean isNumber(String number) {
        return Validator.isNumber(number);
    }
}
