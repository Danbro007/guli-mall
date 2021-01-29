package com.danbro.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @Classname ProductApplication
 * @Description TODO
 * @Date 2021/1/27 21:52
 * @Created by Administrator
 */
@EnableDiscoveryClient
@EnableOpenApi
@SpringBootApplication
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class,args);
    }
}
