package com.danbro.common.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Danrbo
 * @Classname CollectionUtils
 * @Description TODO 容器工具类
 * @Date 2021/2/1 15:34
 */
public class MyCollectionUtils {
    /**
     * 容器是否为空
     *
     * @param collection 容器
     * @return 容器是否为空
     */
    public static Boolean isEmpty(Collection<?> collection) {
        return CollectionUtil.isEmpty(collection);
    }

    public static Boolean isNotEmpty(Collection<?> collection) {
        return collection != null && collection.size() > 0;
    }


    /**
     * 新建一个列表
     *
     * @param values 表内的元素
     * @param <T>    元素的类型
     * @return 列表
     */
    public static <T> ArrayList<T> newArrayList(T... values) {
        return CollUtil.newArrayList(values);
    }
}
