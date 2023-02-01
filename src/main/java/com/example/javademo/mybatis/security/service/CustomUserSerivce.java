package com.example.javademo.mybatis.security.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.javademo.mybatis.entity.User;
import com.example.javademo.mybatis.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserSerivce implements UserDetailsService {
    @Autowired
    UserServiceImpl userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username + "username");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("mobile", username);
        User user = userService.getOne(queryWrapper);
        System.out.println(JSON.toJSONString(user));

        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        // 为用户添加权限与菜单

        // 菜单 left join查询
        // menu entity需要自己建立一下

        // 权限需要转换一下

        return user;
    }
}
