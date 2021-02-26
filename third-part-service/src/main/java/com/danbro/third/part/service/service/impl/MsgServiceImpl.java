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

    @Override
    public void sendCode(String code,String phone) {
        msgComponent.sendCode(code,phone);
    }
}
