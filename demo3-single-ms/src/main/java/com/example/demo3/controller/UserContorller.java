package com.example.demo3.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/demo3")
public class UserContorller {
    @RequestMapping("/user/introduce")
    public Object getInfo() {
        System.out.println("123");
        return new String("123");
    }
}
