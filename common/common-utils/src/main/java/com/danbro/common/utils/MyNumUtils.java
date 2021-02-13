package com.danbro.common.utils;

import java.math.BigDecimal;

/**
 * @Classname MyNumUtils
 * @Description TODO 数字工具类
 * @Date 2021/2/13 8:45
 * @Created by Administrator
 */

public class MyNumUtils {

    /**
     * 判断数字是不是在两个 BigDecimal 数字之间
     * @param num
     * @param num1
     * @param num2
     * @return
     */
    public static Boolean between(BigDecimal num, BigDecimal num1, BigDecimal num2) {
        return num.compareTo(num1) > 0 && num.compareTo(num2) < 0;
    }
}
