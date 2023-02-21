package com.example.javademo.mybatis.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.javademo.mybatis.entity.SkinDetail;
import com.example.javademo.mybatis.mapper.SkinDetailMapper;
import com.example.javademo.mybatis.service.ISkinDetailService;
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
public class SkinDetailServiceImpl extends ServiceImpl<SkinDetailMapper, SkinDetail> implements ISkinDetailService {
}
