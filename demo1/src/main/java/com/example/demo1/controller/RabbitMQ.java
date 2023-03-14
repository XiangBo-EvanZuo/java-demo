package com.example.demo1.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo1/rabbitmq")
class RabbitMQ {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping("/demo")
    public String demo() {
        String queenName = "hello";
        String queenMessage = "ms";
        System.out.println(queenName);
        rabbitTemplate.convertAndSend(queenName, queenMessage);
        System.out.println("rb");
        return "success";
    }
}
