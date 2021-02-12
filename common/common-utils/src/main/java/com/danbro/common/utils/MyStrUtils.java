package com.danbro.common.utils;

import java.sql.Struct;
import java.util.List;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;

/**
 * @author Danrbo
 * @Classname MyStrUtils
 * @Description TODO
 * @Date 2021/2/1 15:31
 */
public class MyStrUtils {
    /**
     * 判断 字符串是否为空
     *
     * @param str 要判断的字符串
     * @return 判断结果
     */
    public static Boolean isNotEmpty(String str) {
        return !StrUtil.isEmpty(str);
    }

    public static Boolean isEmpty(String str) {
        return StrUtil.isEmpty(str);
    }

    public static Boolean isBlank(String str) {
        return StrUtil.isBlank(str);
    }

    public static String replace(CharSequence str, CharSequence searchStr, CharSequence replacement) {
        return StrUtil.replace(str, searchStr, replacement);
    }


    public static String join(CharSequence delimiter, List<String> list) {
        return String.join(delimiter, list);
    }

    /**
     * 通过分割图片的地址获取图片文件名
     * @param imageUrl 图片的地址
     * @return 图片文件名
     */
    public static String getImageName(String imageUrl) {
        String[] split = StrUtil.split(imageUrl, "_");
        return split[split.length - 1];
    }


}
