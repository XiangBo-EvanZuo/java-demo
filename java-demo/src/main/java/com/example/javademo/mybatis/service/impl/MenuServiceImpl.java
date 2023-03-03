package com.example.javademo.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.javademo.mybatis.entity.Menu;
import com.example.javademo.mybatis.mapper.MenuMapper;
import com.example.javademo.mybatis.service.IMenuService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author linlaoshi
 * @since 2022-12-31
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Autowired
    MenuMapper mapper;

    public List<Menu> getUserMenuList(Long userId) {
        return mapper.getUserMenuList(userId);
    }
}
