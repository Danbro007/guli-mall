package com.danbro.order.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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


}