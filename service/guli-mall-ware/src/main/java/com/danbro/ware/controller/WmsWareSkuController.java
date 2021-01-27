package com.danbro.ware.controller;
 
import com.danbro.ware.entity.WmsWareSku;
import com.danbro.ware.service.WmsWareSkuService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-27 21:31:11
 */
@Api(tags = "商品库存(WmsWareSku)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("wmsWareSku")
public class WmsWareSkuController {
    @Autowired
    private  WmsWareSkuService wmsWareSkuService;
 
}