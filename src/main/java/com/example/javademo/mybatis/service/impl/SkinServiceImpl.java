package com.example.javademo.mybatis.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.javademo.mybatis.entity.Skin;
import com.example.javademo.mybatis.mapper.SkinMapper;
import com.example.javademo.mybatis.service.ISkinService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author linlaoshi
 * @since 2022-12-31
 */
@Service
public class SkinServiceImpl extends ServiceImpl<SkinMapper, Skin> implements ISkinService {
}
