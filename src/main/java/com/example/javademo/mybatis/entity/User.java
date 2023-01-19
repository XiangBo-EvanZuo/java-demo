package com.example.javademo.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import com.example.javademo.mybatis.common.Validators.CustomValidators.CaseUpper;
import com.example.javademo.mybatis.common.Validators.Interfaces.Save;
import com.example.javademo.mybatis.common.Validators.Interfaces.Update;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 管理员用户
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(allowSetters = true, value = {"pwd"})
public class User implements Serializable {
    Long id;
    @CaseUpper(message = "用户名必须大写", groups = {Save.class})
    String username;
    @NotEmpty(message = "密码错误")
    String mobile;
    @CaseUpper(message = "密码必须大写", groups = {Update.class})
    @NotEmpty(message = "密码不能为空", groups = Save.class)
    String pwd;
    // 全局配置
//    @TableLogic(value = "0", delval = "1")
    Integer deleted;
    @TableField(select = false)
    Integer notExistField;
    @Version
    Integer version;
    String avatarUrl;
}

