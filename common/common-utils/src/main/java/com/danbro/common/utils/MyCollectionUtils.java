package com.danbro.common.utils;

import cn.hutool.core.collection.CollectionUtil;

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
}
