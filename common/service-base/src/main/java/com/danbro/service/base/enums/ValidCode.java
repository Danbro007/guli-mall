package com.danbro.service.base.enums;

import com.danbro.service.base.interfaces.ResultCode;

/**
 * @author Danrbo
 * @Classname ValidCode
 * @Description TODO
 * @Date 2021/2/4 13:28
 */
public enum ValidCode implements ResultCode {
    /**
     * 校验代码
     */
    VALID_PARAM_FAILURE(5000, "参数校验异常！");
    private String message;
    private Integer code;

    ValidCode(Integer code, String message) {
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
