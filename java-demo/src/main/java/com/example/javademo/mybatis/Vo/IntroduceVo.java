package com.example.javademo.mybatis.Vo;

import com.example.javademo.mybatis.config.EnvVariblesInject;
import lombok.Data;

@Data
public class IntroduceVo {
    String value;
    EnvVariblesInject envVariblesInject;
}
