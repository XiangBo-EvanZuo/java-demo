package com.example.javademo;

import com.alibaba.fastjson.JSON;
import com.example.javademo.mybatis.entity.User;
import com.example.javademo.mybatis.security.jwt.JwtUtil;
import com.example.javademo.mybatis.service.impl.TestDemo;
import com.example.javademo.mybatis.service.impl.UserServiceImpl;
import com.example.javademo.mybatis.utils.bloomfilter.BloomFilterWuPanTest;
import com.example.javademo.mybatis.utils.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Slf4j
@SpringBootTest
@EnableCaching
@EnableTransactionManagement
public class TestLog {
    @Autowired
    RedisService redisService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    TestDemo testDemo;
    @Test
    public void contextLoads() {
        //日志的级别
        //由低到高   trace<debug<info<warn<error
        //可以调整输出的日志级别；日志就只会在这个级别以以后的高级别生效
        //trace 跟踪 debug调试 info 信息 warn 警告 error 错误
        log.trace("这是trace日志...");
        log.debug("这是debug日志...");
        //SpringBoot默认给我们使用的是info级别的，没有指定级别的就用SpringBoot默认规定的级别；root级别
        log.info("这是info日志...");
        log.warn("这是warn日志...");
        log.error("这是error日志...");
    }

    @Test
    public void test() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("123");
        log.info(password);
        System.out.println(password);
        boolean matcher = passwordEncoder.matches("123", "$2a$10$xQ7YJylbYb3a51n.Hcb61.cjeHXXf7H5cJBpqoIeD3zh61/OcD/FO");
        System.out.println(matcher);
    }
    @Test
    public void testJwt() {
        String token = JwtUtil.createToken("123");
        System.out.println(token);
        String username = JwtUtil.getUsernameFromToken(token);
        System.out.println(username);
    }
    @Test
    public void testRedis() {
        redisService.set("test2", "test2", 10L);
        redisService.set("test3", "test3", 1000L);
    }
    @Test
    public void testGetRedis() {
        String value = (String) redisService.get("test2");
        System.out.println("value " + value);
    }
    @Test
    public void testDelRedis() {
        redisService.remove("test2");
        String value = (String) redisService.get("test2");
        System.out.println("value " + value);
    }

    public void service() {
        User user2 = userService.getById(2);
        user2.setPwd("888");
        userService.updateById(user2);
//        throw new IOException();
        throw new RuntimeException();
    }
    @Test
    public void testTransaction() {
        testDemo.demo();
        throw new RuntimeException();
    }
    @Test
    public void testCache() {
        User user = userService.getById(2);
        System.out.println(JSON.toJSONString(user));
    }
    // 测试bloomFilter误判测试
    @Test
    public void bloomFilterWuPanTest() {
        BloomFilterWuPanTest.main(new String[]{""});
    }

}
