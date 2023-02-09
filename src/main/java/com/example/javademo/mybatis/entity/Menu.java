package com.example.javademo.mybatis.entity;

import lombok.Data;

@Data
public class Menu {
    Integer id;
    String path;
    Integer parentId;
    String parentName;
    String title;
    boolean deleted;
}
