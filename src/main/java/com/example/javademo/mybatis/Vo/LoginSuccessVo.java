package com.example.javademo.mybatis.Vo;

import com.example.javademo.mybatis.entity.Menu;
import lombok.Data;

import java.util.List;

@Data
public class LoginSuccessVo {
    String token;
    LoginUserVo user;
    List<Menu> menuList;
}
