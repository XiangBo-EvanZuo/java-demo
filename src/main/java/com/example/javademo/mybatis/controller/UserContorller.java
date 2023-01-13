package com.example.javademo.mybatis.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.javademo.mybatis.common.Errors.PassWordError;
import com.example.javademo.mybatis.entity.LoginVO;
import com.example.javademo.mybatis.common.Validators.Interfaces.Save;
import com.example.javademo.mybatis.entity.User;
import com.example.javademo.mybatis.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserContorller {
    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/login")
    public LoginVO findListItem(@RequestBody @Validated({Save.class}) User user) throws PassWordError {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(!StringUtils.isEmpty(user.getMobile()), "mobile", user.getMobile());
        User res = userService.getOne(queryWrapper);
        String resJSON = JSON.toJSONString(res, true);
        System.out.println(resJSON);
        LoginVO loginRes = new LoginVO();
        if (res == null) {
            throw new PassWordError();
        } else {
            System.out.println("res:" + res.getPwd());
            System.out.println("user:" + user.getPwd());
            System.out.println("getMobile:" + user.getMobile());
            System.out.println("isEmpty:" + StringUtils.isEmpty(user.getMobile()));
            if (res.getPwd().equals(user.getPwd())) {
                loginRes.setResult(true);
                loginRes.setMessage("登陆成功!");
            } else {
                throw new PassWordError();
            }
        }
        return loginRes;
    }
}
