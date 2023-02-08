package com.example.javademo.mybatis.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.javademo.mybatis.Vo.IntroduceVo;
import com.example.javademo.mybatis.Vo.PageType;
import com.example.javademo.mybatis.Vo.QueryUserPageVo;
import com.example.javademo.mybatis.common.Exceptions.*;
import com.example.javademo.mybatis.Vo.LoginVo;
import com.example.javademo.mybatis.common.Validators.Interfaces.Save;
import com.example.javademo.mybatis.entity.Menu;
import com.example.javademo.mybatis.entity.User;
import com.example.javademo.mybatis.service.impl.UserServiceImpl;
import com.example.javademo.mybatis.utils.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
            Object obj = authentication.getPrincipal();
            if (obj instanceof String) {
                vo.setResult(true);
                vo.setMessage("logout anonymousUser");
            } else if (obj instanceof User) {
                User user = (User) authentication.getPrincipal();
                new SecurityContextLogoutHandler().logout(request, response, authentication);
                redisService.remove("token_" + user.getUsername());
                vo.setResult(true);
                vo.setMessage("logout");
            }
        }
        return vo;
    }

    @PreAuthorize("hasAuthority('user:list') AND hasRole('admin')")
    @RequestMapping("/getInfo")
    public User getInfo(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(JSON.toJSONString(authentication.getPrincipal()));
        return (User) authentication.getPrincipal();
    }
    @RequestMapping("/getMenuList")
    public List<Menu> getMenuList(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(JSON.toJSONString(authentication.getPrincipal()));
        User user = (User) authentication.getPrincipal();
        return user.getMenuList();
    }
    @PreAuthorize("hasAuthority('user:list') AND hasRole('admin')")
    @RequestMapping("/list")
    public PageType<List<User>> getUserList(@RequestBody QueryUserPageVo queryUserPageVo) {
        IPage page = new Page(queryUserPageVo.getCurrent(), queryUserPageVo.getPageSize());
        userService.page(page, null);

        System.out.println(page.getPages());
        System.out.println(page.getCurrent());
        System.out.println(page.getRecords());
        System.out.println();
        PageType pageType = new PageType();
        pageType.setData(page.getRecords());
        pageType.setTotal(page.getTotal());
        pageType.setCurrent(page.getCurrent());
        pageType.setSize(page.getSize());
        return pageType;
    }

    @RequestMapping("error")
    public void errorLogin () throws NormalError {
        throw new NormalError();
    }
    @RequestMapping("introduce")
    public IntroduceVo introduce () {
        IntroduceVo introduceVo = new IntroduceVo();
        introduceVo.setValue("introduce");
        return introduceVo;
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
