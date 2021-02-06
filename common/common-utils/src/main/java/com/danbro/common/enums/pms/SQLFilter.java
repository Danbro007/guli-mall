package com.danbro.common.enums.pms;

import com.danbro.common.enums.ValidCode;
import com.danbro.common.exceptions.GuliMallException;
import com.danbro.common.utils.MyStrUtils;

/**
 * @author Danrbo
 * @Classname SQLFilter
 * @Description TODO
 * @Date 2021/2/6 21:15
 */
public class SQLFilter {

    /**
     * SQL注入过滤
     *
     * @param str 待验证的字符串
     */
    public static String sqlInject(String str) {
        if (MyStrUtils.isBlank(str)) {
            return null;
        }
        //去掉'|"|;|\字符
        str = MyStrUtils.replace(str, "'", "");
        str = MyStrUtils.replace(str, "\"", "");
        str = MyStrUtils.replace(str, ";", "");
        str = MyStrUtils.replace(str, "\\", "");

        //转换成小写
        str = str.toLowerCase();

        //非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alter", "drop"};

        //判断是否包含非法字符
        for (String keyword : keywords) {
            if (str.indexOf(keyword) != -1) {
                throw new GuliMallException(ValidCode.CONTAIN_ILLEGAL_CHARACTER);
            }
        }
        return str;
    }
}
