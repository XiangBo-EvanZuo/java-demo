package com.example.demo2.RabbitMqConfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitListenerConfig {

    @RabbitListener(queues = "hello")
    public void rabbitListener(String msg) {

        System.out.println("listen " + msg);
        log.info(msg);
    }
}
