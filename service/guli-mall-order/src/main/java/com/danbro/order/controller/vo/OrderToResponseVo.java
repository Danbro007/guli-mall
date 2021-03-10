package com.danbro.order.controller.vo;

import com.danbro.order.entity.OmsOrder;
import com.danbro.order.entity.OmsOrderItem;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Danrbo
 * @Classname OrderVo
 * @Description TODO 订单vo
 * @Date 2021/3/10 12:09
 */
@Data
@Accessors(chain = true)
public class OrderToResponseVo implements Serializable {
    private OmsOrder order;
    private List<OmsOrderItem> items;
    private BigDecimal payPrice;
    private BigDecimal fare;
}
