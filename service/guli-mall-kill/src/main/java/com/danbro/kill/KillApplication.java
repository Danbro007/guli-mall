package com.danbro.kill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Classname KillApplication
 * @Description TODO
 * @Date 2021/3/18 20:43
 * @Created by Administrator
 */

@EnableRedisHttpSession
@EnableFeignClients
@EnableDiscoveryClient
@EnableSwagger2
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = "com.danbro")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class KillApplication {
    public static void main(String[] args) {
        SpringApplication.run(KillApplication.class, args);
    }
}
