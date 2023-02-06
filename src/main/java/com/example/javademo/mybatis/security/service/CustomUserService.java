package com.example.javademo.mybatis.security.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.javademo.mybatis.entity.Menu;
import com.example.javademo.mybatis.entity.User;
import com.example.javademo.mybatis.service.impl.MenuServiceImpl;
import com.example.javademo.mybatis.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomUserService implements UserDetailsService {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    MenuServiceImpl menuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("mobile", username);
        User user = userService.getOne(queryWrapper);

        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        // @xiangbo todo 5表
        // 菜单 left join查询
        // 为用户添加权限与菜单
        // menu entity需要自己建立一下
        // 权限需要转换一下

        List<String> roles = new ArrayList<>();
        if (username.equals("test-admin")) {
            roles.add("admin");
        }
        List<Menu> menuList = menuService.getUserMenuList(user.getId());
        user.setMenuList(menuList);
        List<String> permissions = new ArrayList<>();
        permissions.add("user:list");

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(1 + 2);
        List<SimpleGrantedAuthority> roleAuthorities = roles.stream()
                //给角色名称增加前缀ROLE_
                .map(role -> "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        // 将授权许可信息转换为SimpleGrantedAuthority对象类型
        List<SimpleGrantedAuthority> permissionAuthorities = permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        /*将角色和授权许可合并到grantedAuthorities列表*/
        grantedAuthorities.addAll(roleAuthorities);
        grantedAuthorities.addAll(permissionAuthorities);
        user.setAuthorities(grantedAuthorities);
        return user;
    }
}
