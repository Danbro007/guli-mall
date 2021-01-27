package com.danbro.order.controller;
 
import com.danbro.order.entity.OmsOrderSetting;
import com.danbro.order.service.OmsOrderSettingService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-27 21:30:18
 */
@Api(tags = "订单配置信息(OmsOrderSetting)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("omsOrderSetting")
public class OmsOrderSettingController {
    @Autowired
    private  OmsOrderSettingService omsOrderSettingService;
 
}