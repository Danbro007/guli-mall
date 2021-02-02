package com.danbro.service.base.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author liweimo
 * @Classname ResultBean
 * @Description TODO 响应体
 * @Date 2021/1/28 22:16
 * @Created by Administrator
 */
@Data
@NoArgsConstructor
public class ResultBean<T> implements Serializable {

    private T data;
    private Integer code;
    private String message;
    private Boolean success;
    private List<String> errorList;

    /**
     * 只返回响应代码和响应消息的成功响应体
     *
     * @return 响应体
     */
    public static ResultBean ofSuccess() {
        ResultBean resultBean = new ResultBean();
        resultBean.setSuccess(true);
        resultBean.setCode(ResponseCode.SUCCESS.getCode());
        resultBean.setMessage(ResponseCode.SUCCESS.getMessage());
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
        ResultBean resultBean = ofSuccess();
        resultBean.setData(t);
        return resultBean;
    }

    /**
     * 只返回响应代码和响应消息的失败响应体
     *
     * @return 响应体
     */
    public static ResultBean ofFailure() {
        ResultBean resultBean = new ResultBean();
        resultBean.setSuccess(false);
        resultBean.setCode(ResponseCode.SUCCESS.getCode());
        resultBean.setMessage(ResponseCode.SUCCESS.getMessage());
        return resultBean;
    }

    /**
     * 返回错误信息列表的失败响应体
     *
     * @param errorList 错误信息列表
     * @return 响应体
     */
    public static ResultBean ofFailure(List<String> errorList) {
        ResultBean resultBean = ofFailure();
        resultBean.setErrorList(errorList);
        return resultBean;
    }


}
