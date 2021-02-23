package com.danbro.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Classname SearchApplication
 * @Description TODO
 * @Date 2021/2/15 16:20
 * @Created by Administrator
 */
@EnableFeignClients
@ComponentScan(basePackages = "com.danbro")
@EnableDiscoveryClient
@EnableSwagger2
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class, args);
    }
}
