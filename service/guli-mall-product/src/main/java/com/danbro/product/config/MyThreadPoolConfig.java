package com.danbro.product.config;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname MyThreadCOnfig
 * @Description TODO 线程池配置类
 * @Date 2021/2/25 17:09
 * @Created by Administrator
 */
@EnableConfigurationProperties
@Configuration
public class MyThreadPoolConfig {
    @Bean
    ThreadPoolExecutor threadPoolExecutor(ThreadPoolProperties threadPoolProperties) {
        return new ThreadPoolExecutor(
                threadPoolProperties.getCorePoolSize(),
                threadPoolProperties.getMaximumPoolSize(),
                threadPoolProperties.getKeepAliveTime(),
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(100000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }
}