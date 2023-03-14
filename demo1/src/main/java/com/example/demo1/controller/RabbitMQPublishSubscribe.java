package com.example.demo1.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo1/rabbitmq/")
class RabbitMQPublishSubscribe {

    String exchangeName = "Fanout1";
    String queenMessage = "Fanout1";
    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping("/fanout")
    public String workQueen() {
        rabbitTemplate.convertAndSend(exchangeName, "", queenMessage);
        return "workQueen success";
    }
}
