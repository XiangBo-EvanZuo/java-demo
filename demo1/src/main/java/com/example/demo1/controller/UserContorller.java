package com.example.demo1.controller;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserContorller {
    @Autowired
    RestTemplate restTemplate;
    @RequestMapping("/introduce")
    public Demo getInfo() {
        String url = "http://demo2/user/introduce";
        Object res = restTemplate.getForEntity(url, Object.class).getBody();
        System.out.println(res);
        String url2 = "http://javademo/user/introduce";
        Object res2 = restTemplate.getForEntity(url2, Object.class).getBody();
        System.out.println(res2);
        Demo d = new Demo();
        d.setRes1(res);
        d.setRes2(res2);
        System.out.println(d);
        return d;
    }
}

@Data
class Demo {
    private Object res1;
    private Object res2;
}
