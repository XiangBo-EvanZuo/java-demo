package com.example.demo1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/demo1/rabbitmq/")
class RabbitMQPublishSubscribe {

    String exchangeName = "Fanout1";
    String exchangeNameDirect = "DirectExchange";
    String queenMessage = "Fanout1";
    String queenMessageDirect = "DirectExchange msg ";
    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping("/fanout")
    public String fanout() {
        rabbitTemplate.convertAndSend(exchangeName, "", queenMessage);
        return "workQueen success";
    }


    @RequestMapping("/direct")
    public String direct(@RequestParam("routingKey") String routingKey) {
        log.info(routingKey);
        rabbitTemplate.convertAndSend(exchangeNameDirect, routingKey, queenMessageDirect + routingKey);
        return "queenMessageDirect success";
    }
}
