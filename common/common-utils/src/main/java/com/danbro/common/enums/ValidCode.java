package com.danbro.common.enums;

import com.danbro.common.interfaces.ResultCode;

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
    VALID_PARAM_FAILURE(5000, "参数校验异常！"),
    VALID_FORMAT_FAILURE(5001, "参数类型不正确！"),
    CONTAIN_ILLEGAL_CHARACTER(5002, "包含非法字符串！");
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
