package com.danbro.service.base.enums;

import com.danbro.service.base.interfaces.ResultCode;

/**
 * @author Danrbo
 * @Classname ResponseCode
 * @Description TODO 响应码枚举类
 * @Date 2021/1/29 10:49
 */
public enum ResponseCode implements ResultCode {
    /**
     * 基本响应代码
     */
    UNKNOWN_EXCEPTION("未知异常", 1000),
    SUCCESS("成功", 2000),
    FAILURE("失败", 4000);

    private String message;
    private Integer code;

    ResponseCode(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}