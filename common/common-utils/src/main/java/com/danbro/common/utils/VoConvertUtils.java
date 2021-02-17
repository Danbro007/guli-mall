package com.danbro.common.utils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Classname VoListUtils
 * @Description TODO
 * @Date 2021/2/17 18:37
 * @Created by Administrator
 */
public class VoConvertUtils {
    public static <V> List<V> batchConvertToVo(List<?> entityList, Class<V> clazz) {
        return entityList.stream().map(e -> {
            V newInstance = MyReflectUtils.getNewInstance(clazz);
            MyBeanUtils.copyProperties(e, newInstance);
            return newInstance;
        }).collect(Collectors.toList());
    }

    public static <V> V convertToVo(Object entity, Class<V> clazz) {
        V newInstance = MyReflectUtils.getNewInstance(clazz);
        MyBeanUtils.copyProperties(entity, newInstance);
        return newInstance;
    }
}
