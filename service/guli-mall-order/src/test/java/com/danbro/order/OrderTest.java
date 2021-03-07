package com.danbro.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Classname OrderTest
 * @Description TODO
 * @Date 2021/3/7 16:46
 * @Created by Administrator
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTest {
    @Autowired
    AmqpAdmin admin;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void createExchange() {
        DirectExchange directExchange = new DirectExchange("hello-java-exchange", true, false);
        admin.declareExchange(directExchange);
    }

    @Test
    public void createQueue() {
        Queue queue = new Queue("danbro", true);
        admin.declareQueue(queue);
    }

    @Test
    public void createBinding() {
        Binding binding = new Binding("danbro", Binding.DestinationType.QUEUE,"hello-java-exchange","hello.java",null);
        admin.declareBinding(binding);
    }

    @Test
    public void sendMessage() {
        rabbitTemplate.convertAndSend("hello-java-exchange","hello.java","你好！");
    }
}
