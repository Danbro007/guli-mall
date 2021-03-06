package com.danbro.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Danrbo
 * @Classname ApiGatewayApplication
 * @Description TODO 网关主启动类
 * @Date 2021/1/29 13:50
 */
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.danbro")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
