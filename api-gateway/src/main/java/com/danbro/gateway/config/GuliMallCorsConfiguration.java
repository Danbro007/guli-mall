package com.danbro.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @author Danrbo
 * @Classname GuliMallCorsConfiguration
 * @Description TODO 跨域请求的配置
 * @Date 2021/2/1 10:42
 */
@Configuration
public class GuliMallCorsConfiguration {

    @Bean
    public CorsWebFilter getCorsWebFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许支持cookie来跨域
        corsConfiguration.setAllowCredentials(true);
        // 允许请求的方式来跨域
        corsConfiguration.addAllowedMethod("*");
        // 允许请求的来源来跨域
        corsConfiguration.addAllowedOrigin("*");
        // 允许的请求头来跨域
        corsConfiguration.addAllowedHeader("*");
        // 配置配置源
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        // 跨域的路径
        configurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsWebFilter(configurationSource);
    }
}
