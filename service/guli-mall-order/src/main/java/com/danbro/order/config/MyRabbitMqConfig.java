package com.danbro.order.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Classname MyRabbitMqConfig
 * @Description TODO RabbitMq 配置类
 * @Date 2021/3/7 17:06
 * @Created by Administrator
 */
@Component
public class MyRabbitMqConfig {
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
