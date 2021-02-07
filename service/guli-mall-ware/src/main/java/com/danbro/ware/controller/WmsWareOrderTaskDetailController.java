package com.danbro.ware.controller;
 
import com.danbro.ware.entity.WmsWareOrderTaskDetail;
import com.danbro.ware.service.WmsWareOrderTaskDetailService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-28 19:06:16
 */
@Api(tags = "库存工作单(WmsWareOrderTaskDetail)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("wmsWareOrderTaskDetail")
public class WmsWareOrderTaskDetailController {
    @Autowired
    private  WmsWareOrderTaskDetailService wmsWareOrderTaskDetailService;
 
}