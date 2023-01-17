package com.example.javademo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.example.javademo.mybatis.common.Exceptions.PassWordError;
import com.example.javademo.mybatis.entity.Skin;
import com.example.javademo.mybatis.entity.User;
import com.example.javademo.mybatis.service.impl.SkinServiceImpl;
import com.example.javademo.mybatis.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class TestApp {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    SkinServiceImpl skinService;

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

    @Test
    public void testPage() {
        IPage page = new Page(1, 2);
        LambdaQueryWrapper<Skin> lambdaQueryChainWrapper = new LambdaQueryWrapper();
        lambdaQueryChainWrapper.select(Skin::getName, Skin::getPrice);
        lambdaQueryChainWrapper.ge(false, Skin::getPrice, 100);
        skinService.page(page, lambdaQueryChainWrapper);
        System.out.println(page.getPages());
        System.out.println(page.getCurrent());
        System.out.println(page.getRecords());
        System.out.println(page.getTotal());
    }
    @Test
    public void testShadowQuery() {
        QueryWrapper queryWrapper = new QueryWrapper<Skin>();
        queryWrapper.select("count(*) as count, name");
        queryWrapper.groupBy("name");
        List<Map<String, Skin>> res = skinService.listMaps(queryWrapper);
        System.out.println(res.toString());
    }
//    @Test
    public void testLogicDelete() {
//        userService.removeById(2L);
    }
    @Test
    public void testOptimiseLockInterceptor() {
        User tageOperatetUser1 = userService.getById(2L);
        User tageOperatetUser2 = userService.getById(2L);
        // user1
        tageOperatetUser1.setUsername("111-new");
        userService.updateById(tageOperatetUser1);
        // user2
        tageOperatetUser2.setUsername("222-new");
        userService.updateById(tageOperatetUser2);
        // 两个人拿到同一个vmersion
    }

    @Test
    public void testError() throws PassWordError {
        try {
            throw new PassWordError();

        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
    }
}
