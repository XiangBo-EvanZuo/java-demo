package com.example.demo2.RabbitMqConfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PublishSubscribeConfig {
    // Fanout 1
    @Bean
    FanoutExchange diFanoutExchange() {
        return new FanoutExchange("Fanout1");
    };
    // queue1

    @Bean
    Queue idQueue1() {
        return new Queue("Fanout1.queen1");
    }
    // queue2
    @Bean
    Queue idQueue2() {
        return new Queue("Fanout1.queen2");
    }
    // Fanout1 bind q1, q2

    @Bean
    Binding idBinding(Queue idQueue1, FanoutExchange diFanoutExchange) {
        return BindingBuilder.bind(idQueue1).to(diFanoutExchange);
    };

    @Bean
    Binding idBinding2(Queue idQueue2, FanoutExchange diFanoutExchange) {
        return BindingBuilder.bind(idQueue2).to(diFanoutExchange);
    };
}