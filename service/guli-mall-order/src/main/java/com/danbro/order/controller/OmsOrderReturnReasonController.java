package com.danbro.order.controller;
 
import com.danbro.order.entity.OmsOrderReturnReason;
import com.danbro.order.service.OmsOrderReturnReasonService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-28 18:50:27
 */
@Api(tags = "退货原因(OmsOrderReturnReason)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("omsOrderReturnReason")
public class OmsOrderReturnReasonController {
    @Autowired
    private  OmsOrderReturnReasonService omsOrderReturnReasonService;
 
}