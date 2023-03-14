package com.example.demo1;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import micro.service.demo.clients.IDemo2Client;
import micro.service.demo.clients.IJavaDemoClient;

@SpringBootApplication
@EnableFeignClients(clients = {IDemo2Client.class, IJavaDemoClient.class})
public class Demo1Application {
    public static void main(String[] args) {

        SpringApplication.run(Demo1Application.class, args);
        System.out.println("no ach=");
    }

    @Bean
    @LoadBalanced
    public RestTemplate demo() {
        return new RestTemplate();
    }
}
