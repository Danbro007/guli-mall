package com.danbro.common.utils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Classname VoListUtils
 * @Description TODO list或者对象的类相关转化工具
 * @Date 2021/2/17 18:37
 * @Created by Administrator
 */
public class ConvertUtils {
    /**
     * 批量把list的数据转换成指定的类型
     *
     * @param sourceList 目标List
     * @param clazz      要转换的List的元素类型
     * @param <V>        元素的泛型
     * @return 转换成功后的List
     */
    public static <V> List<V> batchConvert(List<?> sourceList, Class<V> clazz) {
        if (MyCollectionUtils.isNotEmpty(sourceList)) {
            return sourceList.stream().map(e -> {
                V newInstance = MyReflectUtils.getNewInstance(clazz);
                MyBeanUtils.copyProperties(e, newInstance);
                return newInstance;
            }).collect(Collectors.toList());
        }
        throw new NullPointerException();
    }

    public static <V> V convert(Object entity, Class<V> clazz) {
        if (MyObjectUtils.isNull(entity)) {
            throw new NullPointerException();
        }
        V newInstance = MyReflectUtils.getNewInstance(clazz);
        MyBeanUtils.copyProperties(entity, newInstance);
        return newInstance;
    }
}
