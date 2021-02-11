package com.danbro.common.entity;

import com.danbro.common.enums.ResponseCode;
import com.danbro.common.interfaces.Result;
import com.danbro.common.interfaces.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liweimo
 * @Classname ResultBean
 * @Description TODO 响应体
 * @Date 2021/1/28 22:16
 */
@Data
@NoArgsConstructor
public class ResultBean<T> implements Serializable, Result {

    private T data;
    private Integer code;
    private String msg;
    private Boolean success;

    /**
     * 只返回响应代码和响应消息的成功响应体
     *
     * @return 响应体
     */
    public static <T> ResultBean<T> ofSuccess() {
        ResultBean<T> resultBean = new ResultBean<T>();
        resultBean.setSuccess(true);
        resultBean.setCode(ResponseCode.SUCCESS.getCode());
        resultBean.setMsg(ResponseCode.SUCCESS.getMessage());
        return resultBean;
    }

    /**
     * 成功后返回给前端的数据
     *
     * @param t   数据
     * @param <T> 数据类型
     * @return 返回的数据
     */
    public static <T> ResultBean<T> ofSuccess(T t) {
        ResultBean<T> resultBean = ofSuccess();
        resultBean.setData(t);
        return resultBean;
    }

    /**
     * 只返回响应代码和响应消息的失败响应体
     *
     * @return 响应体
     */
    public static <T> ResultBean<T> ofFailure(ResultCode resultCode) {
        ResultBean<T> resultBean = new ResultBean<>();
        resultBean.setSuccess(false);
        resultBean.setCode(resultCode.getCode());
        resultBean.setMsg(resultCode.getMessage());
        return resultBean;
    }

    @Override
    public void setResultCode(ResultCode resultCode) {
        this.msg = resultCode.getMessage();
        this.code = resultCode.getCode();
    }
}
