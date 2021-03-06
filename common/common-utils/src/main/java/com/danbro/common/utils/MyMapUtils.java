package com.danbro.common.utils;

import cn.hutool.core.map.MapUtil;

import java.util.Map;

/**
 * @author Danrbo
 * @Classname MySetUtils
 * @Description TODO
 * @Date 2021/3/6 16:53
 */
public class MyMapUtils {

    public static Boolean isEmpty(Map<?, ?> map) {
        return MapUtil.isEmpty(map);
    }

    public static Boolean isNotEmpty(Map<?, ?> map) {
        return MapUtil.isNotEmpty(map);
    }
}
