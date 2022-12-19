package com.example.javademo.mybatis.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GetListDao {
    String name;
    BigDecimal minPrice;
    BigDecimal maxPrice;
}
