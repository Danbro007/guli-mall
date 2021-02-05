package com.danbro.common.interfaces;

/**
 * @author Danrbo
 * @Classname ConvertToVo
 * @Description TODO Entity 转换成 Vo 的接口
 * @Date 2021/1/29 11:00
 */
public interface ConvertToVo<E, V> {
    /**
     * Entity 转换成 Vo
     *
     * @param e Entity
     * @return Vo
     */
    V convert(E e);
}
