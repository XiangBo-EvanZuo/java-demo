package com.example.javademo.mybatis.service.impl;

import com.example.javademo.mybatis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TestDemo2 {
    @Autowired
    UserServiceImpl userService;

    @Transactional
    public void demo() {
        User user = userService.getById(2);
        user.setUsername("222222");
        userService.updateById(user);
//        throw new RuntimeException();
    }
}
