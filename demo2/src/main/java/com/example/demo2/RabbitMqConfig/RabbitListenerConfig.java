package com.example.demo2.RabbitMqConfig;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitListenerConfig {

    @RabbitListener(queues = "hello")
    public void rabbitListener(String msg) {
        System.out.println("listen " + msg);
    }
}
