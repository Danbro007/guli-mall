package com.danbro.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Classname ThreadPoolProperties
 * @Description TODO
 * @Date 2021/2/25 17:10
 * @Created by Administrator
 */
@Component
@Data
@ConfigurationProperties(prefix = "thread.pool")
public class ThreadPoolProperties {
    private Integer corePoolSize;
    private Integer maximumPoolSize;
    private Long keepAliveTime;
}
