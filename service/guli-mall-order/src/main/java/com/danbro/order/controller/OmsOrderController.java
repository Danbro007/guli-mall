package com.danbro.order.controller;
 
import com.danbro.order.entity.OmsOrder;
import com.danbro.order.service.OmsOrderService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-28 18:50:27
 */
@Api(tags = "订单(OmsOrder)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("omsOrder")
public class OmsOrderController {
    @Autowired
    private  OmsOrderService omsOrderService;
 
}