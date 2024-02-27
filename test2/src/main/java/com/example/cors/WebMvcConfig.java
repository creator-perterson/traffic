package com.example.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//全局的跨域配置

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");//所有域名都能跨域访问
//                .allowedOrigins("http://localhost:63342")//单独设置只能接受从哪里传来的请求
//                .allowedHeaders("*") //允许所有请求头通过
//                .allowedMethods("*") //允许所有的请求方法
//                .maxAge(30*1000);  //设置探测请求的有效期
    }
}
