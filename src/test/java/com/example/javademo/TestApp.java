package com.example.javademo;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.example.javademo.mybatis.entity.User;
import com.example.javademo.mybatis.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

@SpringBootTest
public class TestApp {
    @Autowired
    UserServiceImpl userService;

    @Test
    public void test() {
        List<User> demo = userService.list(null);
        System.out.println(demo);
    }

    public void testMyBatisPlusGenrator() {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/skin", "root", "root")
                .globalConfig(builder -> {
                    builder.author("xiangbo") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("/Users/linzhe/my-project/demo/src/main/java/"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.example.demo") // 设置父包名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "/Users/linzhe/my-project/demo/src/main/resources")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new VelocityTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }


}
