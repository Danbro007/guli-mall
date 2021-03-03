package com.danbro.common.utils;

import cn.hutool.core.util.IdUtil;

/**
 * @Classname MyRandomUtils
 * @Description TODO
 * @Date 2021/3/3 20:57
 * @Created by Administrator
 */
public class MyRandomUtils {

    public static String randomUUID(){
        return IdUtil.randomUUID();
    }
}
