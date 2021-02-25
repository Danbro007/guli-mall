package com.danbro.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Classname MyWebConfig
 * @Description TODO
 * @Date 2021/2/25 18:56
 * @Created by Administrator
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("login.html").setViewName("login");
        registry.addViewController("reg.html").setViewName("reg");
    }

}
