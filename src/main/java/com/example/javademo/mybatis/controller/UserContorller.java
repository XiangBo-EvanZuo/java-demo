package com.example.javademo.mybatis.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.javademo.mybatis.common.Exceptions.NormalError;
import com.example.javademo.mybatis.common.Exceptions.NotLogin;
import com.example.javademo.mybatis.common.Exceptions.PassWordError;
import com.example.javademo.mybatis.common.Exceptions.PassWordErrorParent;
import com.example.javademo.mybatis.entity.LoginVO;
import com.example.javademo.mybatis.common.Validators.Interfaces.Save;
import com.example.javademo.mybatis.entity.User;
import com.example.javademo.mybatis.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
            throw new PassWordErrorParent();
        } else {
            System.out.println("res:" + res.getPwd());
            System.out.println("user:" + user.getPwd());
            System.out.println("getMobile:" + user.getMobile());
            System.out.println("isEmpty:" + StringUtils.isEmpty(user.getMobile()));
            if (res.getPwd().equals(user.getPwd())) {
                loginRes.setResult(true);
                loginRes.setMessage("登陆成功!");
            } else {
                throw new PassWordErrorParent();
            }
        }
        //这里有一个最最重要的步骤 把当前用户对象放入session当中,这样才能返回JSESSIONID给前端
        request.getSession().setAttribute("user", res);
        return loginRes;
    }

    @RequestMapping("error")
    public void errorLogin () throws NormalError {
        throw new NormalError();
    }

    @RequestMapping("get")
    public User get(HttpServletRequest request) {

        //这里要加入一个很重要的逻辑 判断当前session是否有内容,如果没有内容
        //则说明未登录
        User attribute = (User) request.getSession().getAttribute("user");
        if (attribute == null) {
            //如果atrribute==null,说明未登录
            throw new NotLogin();
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", attribute.getId());
        User result = userService.getOne(queryWrapper);
        if (result == null) {
            throw new NotLogin();
        }
        return result;
    }
}
