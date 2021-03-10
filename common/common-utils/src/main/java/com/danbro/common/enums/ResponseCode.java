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
    RPC_TIME_OUT("远程服务调用超时！", 2000),
    PRODUCT_UP_EXCEPTION("商品上架异常！", 3000),
    FAILURE("失败", 4000),
    CODE_HAVE_SENT("验证码已发送！", 4001),
    USERNAME_IS_EXIST("用户已存在！", 4002),
    PHONE_IS_EXIST("手机号已存在！", 4003),
    MEMBER_PASSWORD_ERROR("用户密码错误！", 4004),
    WECHAT_LOGIN_FAILURE("微信登录失败！", 4005),
    VALID_PRICE_FAILURE("商品验价失败！", 4007),
    LOCK_STOCK_FAILURE("锁库存失败！", 4008),

    /**
     * 业务代码
     */
    NOT_FOUND("查找的对象不存在！", 5000),
    INSERT_FAILURE("添加失败！", 5100),
    UPDATE_FAILURE("修改失败！", 5200),
    DELETE_FAILURE("删除失败！", 5300),
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
