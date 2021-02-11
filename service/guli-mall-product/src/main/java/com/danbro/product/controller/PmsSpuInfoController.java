package com.danbro.product.controller;

import com.danbro.product.service.PmsSpuInfoService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author makejava
 * @since 2021-01-28 18:56:55
 */
@Api(tags = "spu信息(PmsSpuInfo)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("pmsSpuInfo")
@Setter
public class PmsSpuInfoController {
    private  PmsSpuInfoService pmsSpuInfoService;

 
}