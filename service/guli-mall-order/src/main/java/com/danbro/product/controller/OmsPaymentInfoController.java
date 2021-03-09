package com.danbro.product.controller;

import com.danbro.product.service.OmsPaymentInfoService;
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
@Api(tags = "支付信息表(OmsPaymentInfo)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("omsPaymentInfo")
public class OmsPaymentInfoController {
    @Autowired
    private  OmsPaymentInfoService omsPaymentInfoService;
 
}