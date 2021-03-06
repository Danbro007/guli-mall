package com.danbro.auth.service.impl;

import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpSession;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;
import com.danbro.auth.config.WeChatProperties;
import com.danbro.auth.controller.dto.WeChatReturnAccessTokenDto;
import com.danbro.auth.controller.dto.WeChatUserInfoDto;
import com.danbro.auth.controller.vo.MemberRegisterParamVo;
import com.danbro.auth.enums.MemberSourceType;
import com.danbro.auth.rpc.ThirdPartServiceClient;
import com.danbro.auth.rpc.UmsClient;
import com.danbro.auth.service.AuthService;
import com.danbro.common.dto.UmsMemberVo;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.exceptions.GuliMallException;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.common.utils.MyJSONUtils;
import com.danbro.common.utils.MyStrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

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

    @Autowired
    WeChatProperties weChatProperties;

    @Autowired
    UmsClient umsClient;

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
        redisTemplate.opsForValue().set(CODE_PREFIX + phone, newCode, 60, TimeUnit.SECONDS);
    }

    @Override
    public String register(MemberRegisterParamVo registerParamVo) {
        return null;
    }

    @Override
    public String wxLogin(String code, HttpSession session) {
        if (MyStrUtils.isNotEmpty(code)) {
            String url = String.format(weChatProperties.getTokenUrl(), weChatProperties.getAppId(), weChatProperties.getAppSecret(), code);
            String tokenInfo = HttpUtil.get(url);
            if (MyStrUtils.isNotEmpty(tokenInfo)) {
                WeChatReturnAccessTokenDto tokenDto = MyJSONUtils.parseObject(tokenInfo, WeChatReturnAccessTokenDto.class);
                if (MyStrUtils.isNotEmpty(tokenDto.getAccessToken()) && MyStrUtils.isNotEmpty(tokenDto.getOpenid())) {
                    String userInfo = HttpUtil.get(String.format(weChatProperties.getWeChatUserInfoUrl(), tokenDto.getAccessToken(), tokenDto.getOpenid()));
                    WeChatUserInfoDto weChatUserInfoDto = MyJSONUtils.parseObject(userInfo, WeChatUserInfoDto.class);
                    UmsMemberVo memberVo = new UmsMemberVo();
                    MyBeanUtils.copyProperties(weChatUserInfoDto, memberVo);
                    MyBeanUtils.copyProperties(tokenDto, memberVo);
                    memberVo.setSourceType(MemberSourceType.WECHAT);
                    memberVo.setStatus(true);
                    // 登录返回token
                    UmsMemberVo umsMemberVo = MyCurdUtils.rpcResultHandle(umsClient.weChatUserLogin(memberVo));
                    return "redirect:http://gulimall.com";
                }
            }
        }
        // 其他失败都重定向到登录页面
        return "redirect:http://auth.gulimall.com/login.html";
    }
}
