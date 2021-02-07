package com.danbro.common.interfaces;

/**
 * @author Danrbo
 * @Classname ConverToEntity
 * @Description TODO Param 转换成 Entity 的接口
 * @Date 2021/1/29 11:00
 */
public interface ConvertToEntity<E> {
    /**
     * Param 转换成 Entity
     *
     * @param p Param
     * @return Entity
     */
    E convertEntity();
}
