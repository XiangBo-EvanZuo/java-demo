package com.example.demo1;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Demo1ApplicationTests {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    String contextLoads() {
        String queenName = "hello";
        String queenMessage = "ms";
        System.out.println(queenName);
        rabbitTemplate.convertAndSend(queenName, queenMessage);
        System.out.println("rb");
        return "success";
    }

    @Test
    String demo() {
        String queenName = "hello";
        String queenMessage = "ms";
//        System.out.println(queenName);
//        rabbitTemplate.convertAndSend(queenName, queenMessage);
//        System.out.println("rb");
        return "success";
    }
}
