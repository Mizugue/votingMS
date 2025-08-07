package com.hallak.PollRepositoryService.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitDeclarablesConfig {

    @Value("${rabbitmq.queues.polls}")
    private String pollsQueueName;

    @Value("${rabbitmq.queues.ballots}")
    private String ballotsQueueName;


    @Bean
    public Declarables declarables() {
        Queue polls = new Queue(pollsQueueName, true, false, false);
        Queue ballots = new Queue(ballotsQueueName, true, false, false);

        DirectExchange exchange = ExchangeBuilder.directExchange("app.exchange").durable(true).build();

        Binding bPolls = BindingBuilder.bind(polls).to(exchange).with("polls.routing");
        Binding bBallots = BindingBuilder.bind(ballots).to(exchange).with("ballots.routing");

        return new Declarables(polls, ballots, exchange, bPolls, bBallots);
    }


    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory cf) {
        RabbitAdmin admin = new RabbitAdmin(cf);
        admin.setAutoStartup(true);
        return admin;
    }
}

