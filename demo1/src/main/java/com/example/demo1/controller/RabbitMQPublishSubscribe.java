package com.example.demo1.controller;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/demo1/rabbitmq/")
class RabbitMQPublishSubscribe {

    String exchangeName = "Fanout1";
    String queenMessage = "Fanout1";
    String queenMessageDirect = "DirectExchange msg ";
    String exchangeNameDirect = "DirectExchange";
    String queenTopicExchangeName = "TopicExchange";
    String queenMessageTopic = "TopicExchange msg ";
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

    @RequestMapping("/topic")
    public String topic(@RequestParam("routingKey") String routingKey) {
        log.info(routingKey);
        var publisherMap = new HashMap<String, Object>();
        publisherMap.put("routingKey", routingKey);
        publisherMap.put("queenMessageTopic", queenMessageTopic);
        rabbitTemplate.convertAndSend(queenTopicExchangeName, routingKey, publisherMap);
        return "queenMessageDirect success";
    }
}
