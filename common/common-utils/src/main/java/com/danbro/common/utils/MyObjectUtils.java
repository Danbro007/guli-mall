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

    public static Boolean isNull(Object o) {
        return ObjectUtil.isNull(o);
    }

    public static Boolean isNotNull(Object o) {
        return !ObjectUtil.isNull(o);
    }


}
