package com.danbro.third.part.service.service;

public interface MsgService {
    /**
     * 发送验证码
     * @param code 验证码
     * @param phone 手机号
     */
    void sendCode(String code,String phone);
}
