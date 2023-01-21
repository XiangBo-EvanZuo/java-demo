package com.example.javademo.mybatis.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.javademo.mybatis.common.Exceptions.*;
import com.example.javademo.mybatis.entity.LoginVO;
import com.example.javademo.mybatis.common.Validators.Interfaces.Save;
import com.example.javademo.mybatis.entity.User;
import com.example.javademo.mybatis.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
@Slf4j
@RestController
@RequestMapping("/user")
public class UserContorller {
    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/login")
    public LoginVO findListItem(HttpServletRequest request, @RequestBody @Validated({Save.class}) User user) throws PassWordError {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(!StringUtils.isEmpty(user.getMobile()), "mobile", user.getMobile());
        User res = userService.getOne(queryWrapper);
        String resJSON = JSON.toJSONString(res, true);
        System.out.println(resJSON);
        LoginVO loginRes = new LoginVO();
        if (res == null) {
            // 用户不存在
            throw new NotUser();
        } else {
            System.out.println("res:" + res.getPwd());
            System.out.println("user:" + user.getPwd());
            System.out.println("getMobile:" + user.getMobile());
            System.out.println("isEmpty:" + StringUtils.isEmpty(user.getMobile()));
            if (res.getPwd().equals(user.getPwd())) {
                loginRes.setResult(true);
                loginRes.setMessage("登陆成功!");
                loginRes.setData(res);
            } else {
                throw new PassWordErrorParent();
            }
        }
        //这里有一个最最重要的步骤 把当前用户对象放入session当中,这样才能返回JSESSIONID给前端
        request.getSession().setAttribute("user", res);
        return loginRes;
    }
    @RequestMapping("/reset")
    public LoginVO comfirmResetPassword(@RequestBody @Validated({Save.class}) User user) throws PassWordError {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(!StringUtils.isEmpty(user.getMobile()), "mobile", user.getMobile());
        User res = userService.getOne(queryWrapper);
        LoginVO loginRes = new LoginVO();
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
    public LoginVO logout(HttpServletRequest request) throws PassWordError {
        User attribute = (User) request.getSession().getAttribute("user");
        if (attribute != null) {
            request.getSession().removeAttribute("user");
        }
        LoginVO vo = new LoginVO();
        vo.setResult(true);
        vo.setMessage("logout");
        return vo;
    }

    @RequestMapping("error")
    public void errorLogin () throws NormalError {
        throw new NormalError();
    }

    @RequestMapping("register")
    public LoginVO get(@RequestBody @Validated({Save.class}) User user) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("mobile", user.getMobile());
        User result = userService.getOne(queryWrapper);
        if (result == null) {
            userService.save(user);
            LoginVO loginVO = new LoginVO();
            loginVO.setResult(true);
            loginVO.setMessage("register成功!");
            return loginVO;
        } else {
            throw new DuplicatedUser();
        }
    }
}
