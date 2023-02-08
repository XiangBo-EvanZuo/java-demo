package com.example.javademo.mybatis.entity;

import lombok.Data;

@Data
public class Menu {
    Integer id;
    String path;
    String menuPath;
    String owner;
    String title;
}
