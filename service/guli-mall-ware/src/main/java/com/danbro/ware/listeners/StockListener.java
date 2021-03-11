package com.danbro.ware.listeners;

import com.danbro.ware.config.MyRabbitMqConfig;
import com.danbro.ware.entity.WmsWareOrderTaskDetail;
import com.danbro.ware.service.WmsWareSkuService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * @author Danrbo
 * @Classname StockListener
 * @Description TODO
 * @Date 2021/3/11 15:55
 */
@Component
public class StockListener {

    @Autowired
    WmsWareSkuService wmsWareSkuService;

    /**
     * 到延迟队列里取获取要解锁库存的商品
     *
     * @param detailList 要解锁库存的商品列表
     * @param message    消息对象
     */
    @RabbitListener(queues = MyRabbitMqConfig.STOCK_RELEASE_STOCK_QUEUE)
    public void releaseLock(List<WmsWareOrderTaskDetail> detailList, Message message, Channel channel) throws IOException {
        try {
            wmsWareSkuService.releaseStock(detailList);
            // 解锁成功就修改 wms_ware_order_task_detail 的库存状态为释放状态
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }
}
