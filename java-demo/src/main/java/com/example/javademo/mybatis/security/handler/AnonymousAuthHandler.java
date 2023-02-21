package com.example.javademo.mybatis.security.handler;

import com.alibaba.fastjson.JSON;
import com.example.javademo.mybatis.common.Result.ResultData;
import com.example.javademo.mybatis.common.Result.ReturnCode;
import com.example.javademo.mybatis.security.Exception.CustomAuthenticationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// 密码校验未通过
@Component
public class AnonymousAuthHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        ResultData resultData = null;
        if (authException instanceof BadCredentialsException) {
            resultData = ResultData.fail(ReturnCode.PassWordError.getCode(), ReturnCode.PassWordError.getMessage());
        } else if (authException instanceof UsernameNotFoundException) {
            resultData = ResultData.fail(ReturnCode.NotLogin.getCode(), authException.getMessage());
        } else if (authException instanceof CustomAuthenticationException) {
            resultData = ResultData.fail(ReturnCode.NotLogin.getCode(), authException.getMessage());
        } else if (authException instanceof AuthenticationException) {
            resultData = ResultData.fail(ReturnCode.NotLogin.getCode(), ReturnCode.NotLogin.getMessage());
        } else {
            resultData = ResultData.fail(ReturnCode.NotLogin.getCode(), "未知的auth错误 " + authException.getMessage());
        }
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(JSON.toJSONString(resultData));
        out.flush();
    }
}
