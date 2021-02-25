package com.danbro.third.part.service.service.impl;

import java.util.concurrent.TimeUnit;
import cn.hutool.core.util.RandomUtil;
import com.danbro.common.utils.MyStrUtils;
import com.danbro.third.part.service.components.MsgComponent;
import com.danbro.third.part.service.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Classname AuthServiceImpl
 * @Description TODO
 * @Date 2021/2/25 19:15
 * @Created by Administrator
 */
@Service
public class MsgServiceImpl implements MsgService {
    @Autowired
    MsgComponent msgComponent;
    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public void sendCode(String phone) {
        // 缓存里取不到再去生成验证码 超时时间为 60 秒
        String code = redisTemplate.opsForValue().get(String.format("code:%s", phone));
        if (MyStrUtils.isNotEmpty(code)) {
            return;
        }
        String newCode = Integer.toString(RandomUtil.randomInt(1, 9999));
        msgComponent.sendCode(newCode, phone);
        redisTemplate.opsForValue().set(String.format("code:%s", phone), newCode, 60, TimeUnit.SECONDS);
    }
}
