package com.danbro.ware;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Classname CouponApplication
 * @Description TODO
 * @Date 2021/1/28 19:18
 * @Created by Administrator
 */
@EnableRabbit
@EnableDiscoveryClient
@EnableSwagger2
@EnableFeignClients
@SpringBootApplication(exclude = RedisAutoConfiguration.class)
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = "com.danbro")
public class WareApplication {
    public static void main(String[] args) {
        SpringApplication.run(WareApplication.class, args);
    }
}
