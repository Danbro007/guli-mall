package com.danbro.product.controller;
 
import com.danbro.product.entity.PmsSpuInfo;
import com.danbro.product.service.PmsSpuInfoService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-27 21:30:43
 */
@Api(tags = "spu信息(PmsSpuInfo)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("pmsSpuInfo")
public class PmsSpuInfoController {
    @Autowired
    private  PmsSpuInfoService pmsSpuInfoService;
 
}