package com.example.javademo.mybatis.Vo;

import com.example.javademo.mybatis.entity.User;
import lombok.Data;

@Data
public class LoginVo {
    Boolean result;
    String message;
    User data;
}
