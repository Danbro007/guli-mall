package com.danbro.common.utils;

import com.alibaba.fastjson.JSON;

/**
 * @Classname MyJsonUtils
 * @Description TODO Json工具
 * @Date 2021/2/26 20:07
 * @Created by Administrator
 */
public class MyJSONUtils {

    /**
     * 把json字符串转换成指定的对象
     *
     * @param json  json 字符串
     * @param clazz 转换的对象类型
     * @param <T>   转换成的对象
     * @return
     */
    public static  <T> T parseObject(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }
}
