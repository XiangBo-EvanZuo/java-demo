package com.example.javademo.mybatis.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.javademo.mybatis.common.Exceptions.*;
import com.example.javademo.mybatis.Vo.LoginVo;
import com.example.javademo.mybatis.common.Validators.Interfaces.Save;
import com.example.javademo.mybatis.entity.User;
import com.example.javademo.mybatis.service.impl.UserServiceImpl;
import com.example.javademo.mybatis.utils.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserContorller {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    RedisService redisService;
    @RequestMapping("/reset")
    public LoginVo comfirmResetPassword(@RequestBody @Validated({Save.class}) User user) throws PassWordError {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(!StringUtils.isEmpty(user.getMobile()), "mobile", user.getMobile());
        User res = userService.getOne(queryWrapper);
        LoginVo loginRes = new LoginVo();
        if (res == null) {
            // 用户不存在
            throw new NotUser();
        } else {
            res.setPwd(user.getPwd());
            userService.saveOrUpdate(res);
            loginRes.setData(res);
        }
        return loginRes;
    }
    @RequestMapping("/logout")
    public LoginVo logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginVo vo = new LoginVo();
        if (authentication == null) {
            vo.setResult(true);
            vo.setMessage("logout ready authentication not exist");
        } else {
            User user = (User) authentication.getPrincipal();
            System.out.println(JSON.toJSONString(user));
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            redisService.remove("token_" + user.getUsername());
            vo.setResult(true);
            vo.setMessage("logout");
        }
        return vo;
    }

    @RequestMapping("error")
    public void errorLogin () throws NormalError {
        throw new NormalError();
    }

    @RequestMapping("register")
    public LoginVo get(@RequestBody @Validated({Save.class}) User user) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("mobile", user.getMobile());
        User result = userService.getOne(queryWrapper);
        if (result == null) {
            userService.save(user);
            LoginVo loginVO = new LoginVo();
            loginVO.setResult(true);
            loginVO.setMessage("register成功!");
            return loginVO;
        } else {
            throw new DuplicatedUser();
        }
    }
}
