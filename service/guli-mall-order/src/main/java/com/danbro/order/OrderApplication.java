package com.danbro.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @Classname CouponApplication
 * @Description TODO
 * @Date 2021/1/28 19:18
 * @Created by Administrator
 */
@EnableDiscoveryClient
@EnableOpenApi
@SpringBootApplication
@ComponentScan(basePackages = "com.danbro")
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
