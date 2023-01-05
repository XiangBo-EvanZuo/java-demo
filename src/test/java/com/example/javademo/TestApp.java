package com.example.javademo;

import com.example.javademo.mybatis.entity.MybatisPlusDemoDao;
import com.example.javademo.mybatis.mapper.MyBatisPlusMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestApp {
    @Autowired
    MyBatisPlusMapper myBatisPlusMapper;

    @Test
    public void test() {
        List<MybatisPlusDemoDao> demo = myBatisPlusMapper.selectList(null);
        System.out.println(demo);
    }
}
