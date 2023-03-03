package com.example.demo1.controller;
import lombok.Data;
import micro.service.demo.clients.IDemo2Client;
import micro.service.demo.clients.IJavaDemoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/user")
public class UserContorller {
    private final Logger log = LoggerFactory.getLogger(UserContorller.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    IJavaDemoClient javaDemoClient;

    @Autowired
    IDemo2Client demo2Client;
    @RequestMapping("/introduce")
    public Demo getInfo() {
        String url = "http://demo2/user/introduce";
        Object res = javaDemoClient.getUserIntroduce();
        System.out.println(res);
        String url2 = "http://javademo/user/introduce";
        Object res2 = demo2Client.getUserIntroduce();
        System.out.println(res2);
        Demo d = new Demo();
        d.setRes1(res);
        d.setRes2(res2);
        System.out.println(d);
        log.debug("debug2222----------");
        System.out.println("debug aaaaa");
        return d;
    }
}

@Data
class Demo {
    private Object res1;
    private Object res2;
}
