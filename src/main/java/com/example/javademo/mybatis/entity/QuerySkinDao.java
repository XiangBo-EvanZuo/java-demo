package com.example.javademo.mybatis.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class QuerySkinDao {
    String name;
    BigDecimal minPrice;
    BigDecimal maxPrice;
}
