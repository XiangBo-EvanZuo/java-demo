package com.example.javademo.mybatis.security.handler;

import com.alibaba.fastjson.JSON;
import com.example.javademo.mybatis.common.Result.ResultData;
import com.example.javademo.mybatis.entity.User;
import com.example.javademo.mybatis.security.jwt.JwtUtil;
import com.example.javademo.mybatis.utils.redis.RedisService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    RedisService redisService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        User user = (User) authentication.getPrincipal();
        String token = JwtUtil.createToken(user.getUsername());
        redisService.set("token_" + token, token, 20L);
        out.write(JSON.toJSONString(ResultData.success(user)));
        out.flush();
    }
}
