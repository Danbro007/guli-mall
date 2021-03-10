package com.danbro.order.controller;

import com.danbro.order.service.OmsOrderItemService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author makejava
 * @since 2021-01-28 18:50:27
 */
@Api(tags = "订单项信息(OmsOrderItem)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("omsOrderItem")
public class OmsOrderItemController {
    @Autowired
    private OmsOrderItemService omsOrderItemService;
 
}