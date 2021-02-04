package com.danbro.service.exceptions;

import com.danbro.service.base.enums.ResponseCode;
import com.danbro.service.base.interfaces.ResultCode;
import lombok.Data;

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
