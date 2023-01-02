package com.example.javademo.mybatis.mapper;

import com.example.javademo.mybatis.entity.SkinDetailDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SkinDetailMapper {
    @Select("select * from skin_detail where id=#{v}")
    public SkinDetailDao findSkinDetailDaoById(Long id);
}
