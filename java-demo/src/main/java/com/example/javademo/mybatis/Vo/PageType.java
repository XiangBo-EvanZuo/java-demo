package com.example.javademo.mybatis.Vo;

import lombok.Data;

@Data
public class PageType<T> {
    T data;
    Long total;
    Long current;
    Long size;
}
