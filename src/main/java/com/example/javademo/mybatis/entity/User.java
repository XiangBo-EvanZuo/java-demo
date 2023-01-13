package com.example.javademo.mybatis.entity;

import com.example.javademo.mybatis.common.Validators.CustomValidators.CaseUpper;
import com.example.javademo.mybatis.common.Validators.Interfaces.Save;
import com.example.javademo.mybatis.common.Validators.Interfaces.Update;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class User {
    @CaseUpper(message = "username必须大写", groups = {Save.class})
    String username;
    @NotEmpty(message = "not empty")
    String mobile;
    @CaseUpper(message = "pwd必须大写", groups = {Update.class})
    String pwd;
    String url;
}

