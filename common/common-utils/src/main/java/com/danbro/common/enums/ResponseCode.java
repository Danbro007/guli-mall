package com.danbro.common.enums;

import com.danbro.common.interfaces.ResultCode;

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
    SUCCESS("成功", 0),
    UNKNOWN_EXCEPTION("未知异常", 1000),
    RPC_TIME_OUT("远程服务调用超时！",2000),
    FAILURE("失败", 4000),

    /**
     * 业务代码
     */
    NOT_FOUND("查找的对象不存在！", 5000),
    INSERT_FAILURE("添加失败！",5100),
    UPDATE_FAILURE("修改失败！",5200),
    DELETE_FAILURE("删除失败！",5300),
    ;


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
