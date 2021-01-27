package com.danbro.order.controller;
 
import com.danbro.order.entity.OmsPaymentInfo;
import com.danbro.order.service.OmsPaymentInfoService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-27 21:30:18
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