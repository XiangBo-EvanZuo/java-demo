package com.example.demo1.controller;

import com.example.demo1.config.Feignconfigs;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@FeignClient(value = "demo2", configuration = Feignconfigs.class)
public interface IDemo2Client {
    @RequestMapping(value = "/user/introduce", consumes = "application/json", produces = "application/json", method = POST)
    Object getUserIntroduce();
}
