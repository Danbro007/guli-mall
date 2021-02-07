package com.danbro.common.utils;


import cn.hutool.core.bean.BeanUtil;

/**
 * @Classname BeanUtils
 * @Description TODO Bean 工具类
 * @Date 2021/1/28 21:28
 * @Created by Administrator
 */
public class MyBeanUtils {

    public static void copyProperties(Object source, Object target, String... ignoreProperties) {
        BeanUtil.copyProperties(source, target,ignoreProperties);
    }
}
