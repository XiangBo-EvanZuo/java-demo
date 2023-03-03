package com.example.demo1.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class Feignconfigs {
    @Bean
    public Logger.Level feignLogLevel(){
        // 配置日志级别
        return Logger.Level.FULL;
    }
}