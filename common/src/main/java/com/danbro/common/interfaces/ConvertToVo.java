package com.danbro.common.interfaces;

public interface ConvertToVo<E, V> {
    V convert(E e);
}
