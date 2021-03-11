package com.danbro.ware.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;


@Configuration
public class MyRabbitMqConfig {

    public static final String STOCK_RELEASE_STOCK_QUEUE = "stock.release.stock.queue";
    public static final String STOCK_EVENT_EXCHANGE = "stock-event-exchange";
    public static final String STOCK_DELAY_QUEUE = "stock.delay.queue";
    public static final String STOCK_LOCKED_ROUTING_KEY = "stock.locked";
    public static final String STOCK_RELEASE_ROUTING_KEY = "stock.release.#";
    public static final int TTL = 30000;

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Exchange stockEventExchange() {
        return new TopicExchange(STOCK_EVENT_EXCHANGE, true, false);
    }

    @Bean
    public Queue stockReleaseStockQueue() {
        return new Queue(STOCK_RELEASE_STOCK_QUEUE, true, false, false);
    }


    @Bean
    public Queue stockDelay() {
        HashMap<String, Object> arguments = new HashMap<>(3);
        arguments.put("x-dead-letter-exchange", STOCK_EVENT_EXCHANGE);
        arguments.put("x-dead-letter-routing-key", "stock.release");
        // 消息过期时间 30秒
        arguments.put("x-message-ttl", TTL);
        return new Queue(STOCK_DELAY_QUEUE, true, false, false, arguments);
    }

    @Bean
    public Binding stockLocked() {
        return new Binding(STOCK_RELEASE_STOCK_QUEUE,
                Binding.DestinationType.QUEUE,
                STOCK_EVENT_EXCHANGE,
                STOCK_RELEASE_ROUTING_KEY,
                null);
    }


    @Bean
    public Binding stockLockedBinding() {
        return new Binding(STOCK_DELAY_QUEUE,
                Binding.DestinationType.QUEUE,
                STOCK_EVENT_EXCHANGE,
                STOCK_LOCKED_ROUTING_KEY,
                null);
    }


}
