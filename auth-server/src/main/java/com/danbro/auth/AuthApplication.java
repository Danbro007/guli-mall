package com.danbro.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Classname AuthApplication
 * @Description TODO
 * @Date 2021/2/25 17:55
 * @Created by Administrator
 */

@EnableConfigurationProperties
@EnableSwagger2
@EnableDiscoveryClient
@EnableFeignClients
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = "com.danbro")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
