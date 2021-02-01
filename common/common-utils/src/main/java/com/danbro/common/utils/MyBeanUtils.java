package com.danbro.common.utils;

import org.springframework.beans.BeanUtils;

/**
 * @Classname BeanUtils
 * @Description TODO Bean 工具类
 * @Date 2021/1/28 21:28
 * @Created by Administrator
 */
public class MyBeanUtils {

    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }
}
