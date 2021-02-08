package com.danbro.common.utils;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;

/**
 * @author Danrbo
 * @Classname MyObjectUtils
 * @Description TODO 对象工具类
 * @Date 2021/2/5 12:42
 */
public class MyObjectUtils {

    public static Boolean isEmpty(Object o) {
        return ObjectUtil.isEmpty(o);
    }

    public static Boolean isNotEmpty(Object o) {
        return !ObjectUtil.isEmpty(o);
    }


}
