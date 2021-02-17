package com.danbro.common.exceptions;

import com.danbro.common.interfaces.ResultCode;

/**
 * @author Danrbo
 * @Classname ValidException
 * @Description TODO 普通异常
 * @Date 2021/2/4 12:37
 */
public class GuliMallException extends RuntimeException {
    private Integer code;
    private String message;

    public GuliMallException(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public GuliMallException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
