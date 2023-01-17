package com.example.javademo.mybatis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final RequestInterceportor myInterceptor;

    @Autowired
    public WebConfig(RequestInterceportor myInterceptor) {
        this.myInterceptor = myInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 可以添加多个拦截器，先添加的拦截器，先被应用
        registry.addInterceptor(myInterceptor)
                .addPathPatterns("/**") // 应用到所有 URL 上
                .excludePathPatterns("/user/login");
    }
}