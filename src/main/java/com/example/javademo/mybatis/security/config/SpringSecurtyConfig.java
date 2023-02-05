package com.example.javademo.mybatis.security.config;

import com.example.javademo.mybatis.security.filter.TokenFilter;
import com.example.javademo.mybatis.security.handler.AnonymousAuthHandler;
import com.example.javademo.mybatis.security.handler.LoginFailHandler;
import com.example.javademo.mybatis.security.handler.LoginSuccessHandler;
import com.example.javademo.mybatis.security.handler.UserAccessDeniedHandler;
import com.example.javademo.mybatis.security.service.CustomUserService;
import com.example.javademo.mybatis.utils.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurtyConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    LoginSuccessHandler LoginSuccessHandler; // 成功handler
    @Autowired
    LoginFailHandler LoginFailHandler; // 失败handler
    @Autowired
    AnonymousAuthHandler anonymousAuthHandler; // 匿名handler
    @Autowired
    UserAccessDeniedHandler userAccessDeniedHandler; // 连接失败handler
    @Autowired
    CustomUserService customUserService;

    @Autowired
    LoginFailHandler loginFailHandler;
    @Autowired
    RedisService redisService;
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 增加过滤器
        http.addFilterBefore(new TokenFilter(customUserService, loginFailHandler, redisService), UsernamePasswordAuthenticationFilter.class);

        http
        .formLogin()
        .loginProcessingUrl("/user/login")
                .usernameParameter("mobile")
                .passwordParameter("pwd")
        .successHandler(LoginSuccessHandler)
        .failureHandler(LoginFailHandler)
        // 关闭csrf
        .and().csrf().disable()
        // 关闭session
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        // 所有的请求都拦截
        .and().authorizeRequests()
        // 屏蔽的请求
        .antMatchers("/user/login", "/user/logout", "/user/register", "/user/reset", "/user/introduce").permitAll()
        // 其他请求都需要验证
        .anyRequest().authenticated()
        .and().exceptionHandling()
        // 匿名用户无权访问
                .authenticationEntryPoint(anonymousAuthHandler)
        // 认证用户无权访问
                .accessDeniedHandler(userAccessDeniedHandler)
                // 支持跨域
                .and().cors().disable();
    }


    // 自定义service
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService).passwordEncoder(new BCryptPasswordEncoder());
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        // 不走过滤器链路
        web.ignoring().antMatchers("/user/introduce", "/user/logout");
    }

    // @xiangbo todo 自定义provider
}
