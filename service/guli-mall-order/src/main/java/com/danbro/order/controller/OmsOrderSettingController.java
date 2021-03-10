package com.danbro.order.controller;

import com.danbro.order.service.OmsOrderSettingService;
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
@Api(tags = "订单配置信息(OmsOrderSetting)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("omsOrderSetting")
public class OmsOrderSettingController {
    @Autowired
    private OmsOrderSettingService omsOrderSettingService;
 
}