package com.example.javademo.mybatis.Vo;

import lombok.Data;

@Data
public class LoginSuccessVo {
    String token;
    LoginUserVo user;
}
