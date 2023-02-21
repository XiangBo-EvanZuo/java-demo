package com.example.javademo.mybatis.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class QuerySkinVo {
    String name;
    BigDecimal minPrice;
    BigDecimal maxPrice;
}
