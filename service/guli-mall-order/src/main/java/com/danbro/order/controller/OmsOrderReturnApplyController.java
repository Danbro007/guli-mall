package com.danbro.order.controller;
 
import com.danbro.order.entity.OmsOrderReturnApply;
import com.danbro.order.service.OmsOrderReturnApplyService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-28 18:50:27
 */
@Api(tags = "订单退货申请(OmsOrderReturnApply)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("omsOrderReturnApply")
public class OmsOrderReturnApplyController {
    @Autowired
    private  OmsOrderReturnApplyService omsOrderReturnApplyService;
 
}