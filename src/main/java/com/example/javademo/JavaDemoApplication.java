package com.example.javademo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.javademo")
public class JavaDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaDemoApplication.class, args);
    }
}
