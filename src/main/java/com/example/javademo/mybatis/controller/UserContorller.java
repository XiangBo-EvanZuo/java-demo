package com.example.javademo.mybatis.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.javademo.mybatis.entity.LoginVO;
import com.example.javademo.mybatis.entity.User;
import com.example.javademo.mybatis.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserContorller {
    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/login")
    public LoginVO findListItem(@RequestBody User user) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(!StringUtils.isEmpty(user.getMobile()), "mobile", user.getMobile());
        User res = userService.getOne(queryWrapper);
        String resJSON = JSON.toJSONString(res, true);
        System.out.println(resJSON);
        LoginVO loginRes = new LoginVO();
        if (res == null) {
            loginRes.setResult(false);
            loginRes.setMessage("no mobile");
        } else {
            System.out.println("res:" + res.getPwd());
            System.out.println("user:" + user.getPwd());
            System.out.println("getMobile:" + user.getMobile());
            System.out.println("isEmpty:" + StringUtils.isEmpty(user.getMobile()));
            if (res.getPwd().equals(user.getPwd())) {
                loginRes.setResult(true);
                loginRes.setMessage("登陆成功!");
            } else {
                loginRes.setResult(false);
                loginRes.setMessage("登陆 wrong password 失败!");
            }
        }
        return loginRes;
    }
}
