package com.example.demo2.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/user")
public class UserContorller {
    @RequestMapping("/introduce")
    public Object getInfo() {
        System.out.println("123");
        return new String("123");
    }
}
