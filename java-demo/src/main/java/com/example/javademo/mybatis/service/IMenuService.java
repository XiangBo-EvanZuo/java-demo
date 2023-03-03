package com.example.javademo.mybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.javademo.mybatis.entity.Menu;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface IMenuService extends IService<Menu> {

    @Cacheable(cacheNames = {"single_book"},key = "#root.targetClass+'.'+#root.methodName+'.'+#p0",
            unless = "#result == null")
    public List<Menu> getUserMenuList(Long userId);
}
