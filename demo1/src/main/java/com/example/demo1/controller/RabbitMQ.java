package com.example.demo1.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo1/rabbitmq")
class RabbitMQ {

    String queenName = "hello";
    String queenMessage = "ms";
    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping("/demo")
    public String demo() {
        rabbitTemplate.convertAndSend(queenName, queenMessage);
        return "demo success";
    }

    @RequestMapping("/workQueen")
    public String workQueen() throws InterruptedException {
        for (int i = 1; i < 50; i++) {
            rabbitTemplate.convertAndSend(queenName, queenMessage + '_' + i);
            Thread.sleep(20);
        }
        return "workQueen success";
    }
}
