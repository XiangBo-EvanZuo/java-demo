package com.example.demo3.RabbitMqConfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitListenerConfig {

    @RabbitListener(queues = "hello")
    public void rabbitListener(String msg) throws InterruptedException {
        System.out.println("listen1 " + msg);
        Thread.sleep(20);
        log.info(msg);
    }

    @RabbitListener(queues = "hello")
    public void rabbitListener2(String msg) throws InterruptedException {

        System.out.println("listen2 " + msg);
        Thread.sleep(200);
        log.info(msg);
    }
}
