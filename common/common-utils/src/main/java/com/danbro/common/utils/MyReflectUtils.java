package com.danbro.common.utils;

import cn.hutool.core.util.ReflectUtil;

/**
 * @Classname MyReflectUtils
 * @Description TODO 反射工具
 * @Date 2021/2/7 19:44
 * @Created by Administrator
 */
public class MyReflectUtils {

    /**
     * 通过反射获取到指定类的实例
     *
     * @param clazz 类
     * @param <T>   泛型
     * @return 反射出的对象
     */
    public static <T> T getNewInstance(Class<T> clazz) {
        return ReflectUtil.newInstance(clazz);
    }

}
