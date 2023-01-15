package com.example.javademo.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.example.javademo.mybatis.common.Validators.CustomValidators.CaseUpper;
import com.example.javademo.mybatis.common.Validators.Interfaces.Save;
import com.example.javademo.mybatis.common.Validators.Interfaces.Update;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class User {
    Long id;
    @CaseUpper(message = "username必须大写", groups = {Save.class})
    String username;
    @NotEmpty(message = "not empty")
    String mobile;
    @CaseUpper(message = "pwd必须大写", groups = {Update.class})
    String pwd;
    // 全局配置
//    @TableLogic(value = "0", delval = "1")
    Integer deleted;
    @TableField(select = false)
    Integer notExistField;
    @Version
    Integer version;
}

