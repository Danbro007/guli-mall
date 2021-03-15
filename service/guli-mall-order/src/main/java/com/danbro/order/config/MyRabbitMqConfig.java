package com.danbro.order.config;

import java.util.HashMap;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Classname MyRabbitMqConfig
 * @Description TODO RabbitMq 配置类
 * @Date 2021/3/7 17:06
 * @Created by Administrator
 */
@Component
public class MyRabbitMqConfig {
    public static final String ORDER_RELEASE_ORDER_QUEUE = "order.release.order.queue";
    public static final String ORDER_EVENT_EXCHANGE = "order-event-exchange";
    public static final String ORDER_DELAY_QUEUE = "order.delay.queue";
    public static final String ORDER_CREATE_ORDER_ROUTING_KEY = "order.create.order";
    public static final String ORDER_RELEASE_ORDER_ROUTING_KEY = "order.release.#";
    public static final String ORDER_RELEASE_OTHER_ROUTING_KEY = "order.release.other.#";
    public static final int TTL = 300000;
    public static final String STOCK_RELEASE_STOCK_QUEUE = "stock.release.stock.queue";


    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Exchange orderEventExchange() {
        return new TopicExchange(ORDER_EVENT_EXCHANGE, true, false);
    }

    @Bean
    public Queue orderDelayQueue() {
        HashMap<String, Object> arguments = new HashMap<>(3);
        arguments.put("x-dead-letter-exchange", ORDER_EVENT_EXCHANGE);
        arguments.put("x-dead-letter-routing-key", "order.release");
        // 消息过期时间 30秒
        arguments.put("x-message-ttl", TTL);
        return new Queue(ORDER_DELAY_QUEUE, true, false, false, arguments);
    }

    @Bean
    public Queue releaseOrderQueue() {
        return new Queue(ORDER_RELEASE_ORDER_QUEUE, true, false, false);
    }

    @Bean
    public Binding createOrderBinding() {
        return new Binding(ORDER_DELAY_QUEUE,
                Binding.DestinationType.QUEUE,
                ORDER_EVENT_EXCHANGE,
                ORDER_CREATE_ORDER_ROUTING_KEY,
                null);
    }

    @Bean
    public Binding releaseOrderBinding() {
        return new Binding(ORDER_RELEASE_ORDER_QUEUE,
                Binding.DestinationType.QUEUE,
                ORDER_EVENT_EXCHANGE,
                ORDER_RELEASE_ORDER_ROUTING_KEY,
                null);
    }

    @Bean
    public Binding releaseOrderOtherBinding() {
        return new Binding(STOCK_RELEASE_STOCK_QUEUE,
                Binding.DestinationType.QUEUE,
                ORDER_EVENT_EXCHANGE,
                ORDER_RELEASE_OTHER_ROUTING_KEY,
                null);
    }

}
