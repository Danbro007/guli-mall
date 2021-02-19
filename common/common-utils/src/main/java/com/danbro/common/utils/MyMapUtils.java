package com.danbro.common.utils;

import java.util.Map;
import cn.hutool.core.map.MapUtil;

/**
 * @Classname MyMapUtils
 * @Description TODO
 * @Date 2021/2/18 14:06
 * @Created by Administrator
 */
public class MyMapUtils {
    public static Boolean isEmpty(Map<?,?> map) {
        return MapUtil.isEmpty(map);
    }
    public static Boolean isNotEmpty(Map<?,?> map) {
        return !MapUtil.isEmpty(map);
    }
}
