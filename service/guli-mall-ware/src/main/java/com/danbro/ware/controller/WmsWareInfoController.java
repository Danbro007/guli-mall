package com.danbro.ware.controller;
 
import com.danbro.ware.entity.WmsWareInfo;
import com.danbro.ware.service.WmsWareInfoService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-27 21:31:11
 */
@Api(tags = "仓库信息(WmsWareInfo)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("wmsWareInfo")
public class WmsWareInfoController {
    @Autowired
    private  WmsWareInfoService wmsWareInfoService;
 
}