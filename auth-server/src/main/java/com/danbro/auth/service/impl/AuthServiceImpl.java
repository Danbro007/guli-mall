package com.danbro.auth.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.danbro.auth.controller.vo.MemberRegisterParamVo;
import com.danbro.auth.rpc.ThirdPartServiceClient;
import com.danbro.auth.service.AuthService;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.exceptions.GuliMallException;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.common.utils.MyStrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Danrbo
 * @Classname AuthServiceImpl
 * @Description TODO
 * @Date 2021/2/25 22:03
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final static String CODE_PREFIX = "sms:code:";

    @Autowired
    ThirdPartServiceClient thirdPartServiceClient;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public void sendCode(String phone) {
        String code = redisTemplate.opsForValue().get(CODE_PREFIX + phone);
        // 验证码没过期
        if (MyStrUtils.isNotEmpty(code)) {
            throw new GuliMallException(ResponseCode.CODE_HAVE_SENT);
        }
        // 缓存里取不到再去生成验证码 超时时间为 60 秒
        String newCode = Integer.toString(RandomUtil.randomInt(1111, 9999));
        System.out.println(newCode);
//        MyCurdUtils.rpcResultHandle(thirdPartServiceClient.sendCode(phone, newCode), true);
        redisTemplate.opsForValue().set(CODE_PREFIX+phone, newCode, 60, TimeUnit.SECONDS);
    }

    @Override
    public String register(MemberRegisterParamVo registerParamVo) {
        return null;
    }
}
