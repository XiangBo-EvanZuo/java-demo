package com.example.javademo.mybatis.security.config;

import java.util.ArrayList;

public class WhiteUserList {
    static public ArrayList<String> getWhiteUserList() {
        // 白名单控制
        ArrayList<String> whiteList = new ArrayList<>();
        whiteList.add("/user/register");
        whiteList.add("/user/reset");
        return whiteList;
    }
}
