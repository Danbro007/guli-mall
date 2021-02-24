package com.danbro.search.controller;

import cn.hutool.core.util.ReUtil;

/**
 * @author Danrbo
 * @Classname Test
 * @Description TODO
 * @Date 2021/2/24 11:44
 */
public class Test {
    public static void main(String[] args) {
        // ?attrs开头
        String str1 = "?attrs=1364072662106714113_绿色&catalogId=225&skuPrice=1_900";
        String all = ReUtil.replaceAll(str1, "^?attrs=.*?\\&", "");
        System.out.println(all);

        String str2 = "?catalogId=225&skuPrice=1_900&attrs=1364072662106714113_绿色";
        String all1 = ReUtil.replaceAll(str2, "attrs=.*?\\", "");
        System.out.println(all1);

        String str3 = "?catalogId=225&attrs=1364072662106714113_绿色&skuPrice=1_900";
        String all2 = ReUtil.replaceAll(str3, "^?attrs=.*?\\", "");
        System.out.println(all1);
    }
}
