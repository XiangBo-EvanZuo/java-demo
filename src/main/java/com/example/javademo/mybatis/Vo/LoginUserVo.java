package com.example.javademo.mybatis.Vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import com.example.javademo.mybatis.common.Validators.CustomValidators.CaseUpper;
import com.example.javademo.mybatis.common.Validators.Interfaces.Save;
import com.example.javademo.mybatis.common.Validators.Interfaces.Update;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;

/**
 * 管理员用户
 */
@Data
public class LoginUserVo {
    Long id;
    String username;
    String avatarUrl;
}

