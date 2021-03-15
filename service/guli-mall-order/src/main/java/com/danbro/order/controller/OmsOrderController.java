package com.danbro.order.controller;

import com.danbro.common.entity.ResultBean;
import com.danbro.order.controller.vo.OmsOrderVo;
import com.danbro.order.service.OmsOrderService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author makejava
 * @since 2021-01-28 18:50:27
 */
@Api(tags = "订单(OmsOrder)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("order")
public class OmsOrderController {
    @Resource
    OmsOrderService omsOrderService;

    @GetMapping("info/{orderSn}")
    public ResultBean<OmsOrderVo> getOrderInfoByOrderSn(@PathVariable String orderSn) {
        return ResultBean.ofSuccess(omsOrderService.getOrderInfoByOrderSn(orderSn));
    }


}