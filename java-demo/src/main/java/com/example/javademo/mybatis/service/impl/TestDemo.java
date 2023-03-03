package com.example.javademo.mybatis.service.impl;

import com.example.javademo.mybatis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TestDemo {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    TestDemo2 testDemo2;

    @Transactional
    public void demo() {
        User user = userService.getById(1);
        user.setUsername("1111");
        userService.updateById(user);
        testDemo2.demo();
//        throw new RuntimeException();
    }
}
