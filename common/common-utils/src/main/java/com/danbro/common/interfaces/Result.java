package com.danbro.common.interfaces;

/**
 * @author Danrbo
 * @Classname Result
 * @Description TODO
 * @Date 2021/2/4 13:12
 */
public interface Result<T> {

    void setSuccess(Boolean bool);

    /**
     * 设置状态码对象
     *
     * @param resultCode 状态码对象
     */
    void setResultCode(ResultCode resultCode);

    /**
     * 设置返回的数据
     *
     * @param t 数据
     */
    void setData(T t);

    void setMessage(String message);

    void setCode(Integer code);

}
