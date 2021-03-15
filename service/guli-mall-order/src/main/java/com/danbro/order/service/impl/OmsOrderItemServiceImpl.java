package com.danbro.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.utils.ConvertUtils;
import com.danbro.common.utils.MyCollectionUtils;
import com.danbro.order.controller.vo.OmsOrderItemVo;
import com.danbro.order.entity.OmsOrderItem;
import com.danbro.order.mapper.OmsOrderItemMapper;
import com.danbro.order.service.OmsOrderItemService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单项信息(OmsOrderItem)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:50:27
 */
@Service
public class OmsOrderItemServiceImpl extends ServiceImpl<OmsOrderItemMapper, OmsOrderItem> implements OmsOrderItemService {

    @Override
    public List<OmsOrderItemVo> getOrderItemListByOrderSn(String orderSn) {
        List<OmsOrderItem> orderItems = this.list(new QueryWrapper<OmsOrderItem>().lambda().eq(OmsOrderItem::getOrderSn, orderSn));
        if (MyCollectionUtils.isNotEmpty(orderItems)) {
            return ConvertUtils.batchConvert(orderItems, OmsOrderItemVo.class);
        }
        return null;
    }
}