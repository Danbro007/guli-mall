package com.danbro.common.utils;

import cn.hutool.core.util.StrUtil;

/**
 * @author Danrbo
 * @Classname MyStrUtils
 * @Description TODO
 * @Date 2021/2/1 15:31
 */
public class MyStrUtils {
    public static Boolean IsNotEmpty(String str) {
        return !StrUtil.isEmpty(str);
    }
}
