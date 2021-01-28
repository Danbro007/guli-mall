package com.danbro.ware.controller;
 
import com.danbro.ware.entity.WmsPurchase;
import com.danbro.ware.service.WmsPurchaseService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-28 19:06:16
 */
@Api(tags = "采购信息(WmsPurchase)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("wmsPurchase")
public class WmsPurchaseController {
    @Autowired
    private  WmsPurchaseService wmsPurchaseService;
 
}