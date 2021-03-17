package com.danbro.seckill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Danrbo
 * @Classname SeckillApplication
 * @Description TODO 秒杀任务
 * @Date 2021/3/16 11:09
 */
@EnableRedisHttpSession
@EnableSwagger2
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
@EnableAsync
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SecKillApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecKillApplication.class, args);
    }
}
