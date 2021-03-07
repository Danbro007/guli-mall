package com.danbro.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author liweimo
 * @Classname ProductApplication
 * @Description TODO
 * @Date 2021/1/27 21:52
 */
@EnableRedisHttpSession
@EnableFeignClients
@EnableDiscoveryClient
@EnableSwagger2
@SpringBootApplication
@ComponentScan(basePackages = "com.danbro")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
