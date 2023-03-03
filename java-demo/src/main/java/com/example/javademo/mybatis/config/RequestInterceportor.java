package com.example.javademo.mybatis.config;

import com.example.javademo.mybatis.common.Exceptions.NotLogin;
import com.example.javademo.mybatis.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// AOP 暂时取消了
@Component
public class RequestInterceportor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws NotLogin {
//        log.info("MyInterceptor.preHandle()");
//        User currentUser = null;
//        HttpSession session = request.getSession();
//        if(session != null){
//            currentUser = (User)session.getAttribute("user");
//        }
//        System.out.println(currentUser);
//        if(currentUser == null){
//            // 说明用户未登录
//            System.out.println("not login");
//            throw new NotLogin();
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        log.info("MyInterceptor.postHandle()");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        log.info("MyInterceptor.afterCompletion()");
    }
}
