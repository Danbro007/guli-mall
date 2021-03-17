package com.danbro.order.listeners;

import java.io.IOException;
import com.danbro.common.dto.SecKillSkuDto;
import com.danbro.order.config.MyRabbitMqConfig;
import com.danbro.order.service.OmsOrderService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname SecKillOrderListener
 * @Description TODO 处理秒杀订单
 * @Date 2021/3/17 19:40
 * @Created by Administrator
 */
@Slf4j
@Service
public class SecKillOrderListener {
    @Autowired
    OmsOrderService omsOrderService;

    @RabbitListener(queues = MyRabbitMqConfig.ORDER_SECKILL_ORDER_QUEUE)
    public void handleSecKillOrder(SecKillSkuDto secKillSkuDto, Message message, Channel channel) throws IOException {
        try {
            log.info(String.format("------------->【接收到秒杀订单：%s】", secKillSkuDto.getOrderSn()));
            omsOrderService.saveSecKillOrder(secKillSkuDto);
            log.info(String.format("------------->【创建秒杀订单成功：%s】", secKillSkuDto.getOrderSn()));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception exception) {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }

}
