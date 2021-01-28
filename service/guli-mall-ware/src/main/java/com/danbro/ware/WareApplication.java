package com.danbro.ware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @Classname CouponApplication
 * @Description TODO
 * @Date 2021/1/28 19:18
 * @Created by Administrator
 */
@EnableOpenApi
@SpringBootApplication
public class WareApplication {
    public static void main(String[] args) {
        SpringApplication.run(WareApplication.class, args);
    }
}
