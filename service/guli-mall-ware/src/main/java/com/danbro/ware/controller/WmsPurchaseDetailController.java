package com.danbro.ware.controller;
 
import com.danbro.ware.entity.WmsPurchaseDetail;
import com.danbro.ware.service.WmsPurchaseDetailService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-27 21:31:11
 */
@Api(tags = "(WmsPurchaseDetail)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("wmsPurchaseDetail")
public class WmsPurchaseDetailController {
    @Autowired
    private  WmsPurchaseDetailService wmsPurchaseDetailService;
 
}