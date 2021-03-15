package com.danbro.order.listeners;

import com.danbro.order.config.MyRabbitMqConfig;
import com.danbro.order.entity.OmsOrder;
import com.danbro.order.service.OmsOrderService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Danrbo
 * @Classname OrderCloseListener
 * @Description TODO 关闭订单的监听器
 * @Date 2021/3/12 14:33
 */
@Slf4j
@Service
public class OrderCloseListener {
    @Autowired
    OmsOrderService omsOrderService;

    @RabbitListener(queues = MyRabbitMqConfig.ORDER_RELEASE_ORDER_QUEUE)
    public void closeOrder(OmsOrder order, Message message, Channel channel) throws IOException {
        try {
            omsOrderService.closeOrder(order);
            log.info("订单关闭");
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            // 关单失败则把订单重新放入队列重试
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }
}
