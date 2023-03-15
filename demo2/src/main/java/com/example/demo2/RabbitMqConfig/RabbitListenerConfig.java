package com.example.demo2.RabbitMqConfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
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

    @RabbitListener(queues = "Fanout1.queen1")
    public void rabbitListenerFanout1(String msg) throws InterruptedException {

        System.out.println("Fanout1.queen1 " + msg);
        Thread.sleep(200);
        log.info(msg);
    }

    @RabbitListener(queues = "Fanout1.queen2")
    public void rabbitListenerFanout2(String msg) throws InterruptedException {
        System.out.println("Fanout1.queen2 " + msg);
        Thread.sleep(200);
        log.info(msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "Direct.queue1"),
            exchange = @Exchange(name = "DirectExchange", type = ExchangeTypes.DIRECT),
            key = {"red", "blue"}
    ))
    public void rabbitListenerDirectQueen1Consumer1(String msg) {
        System.out.println("Direct.queen1 " + msg);
        log.info(msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "Direct.queue2"),
            exchange = @Exchange(name = "DirectExchange", type = ExchangeTypes.DIRECT),
            key = {"red", "yellow"}
    ))
    public void rabbitListenerDirectQueen2Consumer1(String msg) {
        System.out.println("Direct.queen2 " + msg);
        log.info(msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "Topic.queue1"),
            exchange = @Exchange(name = "TopicExchange", type = ExchangeTypes.TOPIC),
            key = "china.#"
    ))
    public void rabbitListenerTopicQueen1Consumer1(String msg) {
        System.out.println("Topic.queen1 " + msg);
        log.info(msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "Topic.queue2"),
            exchange = @Exchange(name = "TopicExchange", type = ExchangeTypes.TOPIC),
            key = "#.news"
    ))
    public void rabbitListenerTopicQueen2Consumer1(String msg) {
        System.out.println("Topic.queen2 " + msg);
        log.info(msg);
    }
}
