package com.danbro.third.part.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.danbro")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ThirdPartServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThirdPartServiceApplication.class, args);
    }

}
