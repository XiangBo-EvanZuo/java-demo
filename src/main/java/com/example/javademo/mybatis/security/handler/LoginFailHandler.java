package com.example.javademo.mybatis.security.handler;

import com.alibaba.fastjson.JSON;
import com.example.javademo.mybatis.common.Result.ResultData;
import com.example.javademo.mybatis.security.Exception.CustomAuthenticationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class LoginFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        ResultData resultData = null;
        System.out.println("eeeeeeeee");
        System.out.println(exception.getMessage());
        if (exception instanceof BadCredentialsException) {
            resultData = ResultData.fail(500, "BadCredentialsException");
        } else if (exception instanceof UsernameNotFoundException) {
            resultData = ResultData.fail(500, exception.getMessage());
        } else if (exception instanceof CustomAuthenticationException) {
            resultData = ResultData.fail(500, "CustomAuthenticationException" + exception.getMessage());
        } else if (exception instanceof AuthenticationException) {
            resultData = ResultData.fail(500, "AuthenticationException");
        } else {
            resultData = ResultData.fail(500, "未知的auth错误");
        }
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(JSON.toJSONString(resultData));
        out.flush();
    }
}
