package com.danbro.service.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * @Classname MySessionConfig
 * @Description TODO SpringSession 配置类
 * @Date 2021/2/27 11:35
 * @Created by Administrator
 */
@Configuration
public class MySessionConfig {
    /**
     * 使用 jackson 来序列化对象
     *
     * @return
     */
    @Bean
    public RedisSerializer<String> springSessionDefaultRedisSerializer() {
        return new StringRedisSerializer();
    }


    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName("GMALLSESSION");
        serializer.setDomainName("gulimall.com");
        return serializer;
    }

}
