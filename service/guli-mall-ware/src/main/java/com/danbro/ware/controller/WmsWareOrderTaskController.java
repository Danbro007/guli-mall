package com.danbro.ware.controller;
 
import com.danbro.ware.entity.WmsWareOrderTask;
import com.danbro.ware.service.WmsWareOrderTaskService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-27 21:31:11
 */
@Api(tags = "库存工作单(WmsWareOrderTask)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("wmsWareOrderTask")
public class WmsWareOrderTaskController {
    @Autowired
    private  WmsWareOrderTaskService wmsWareOrderTaskService;
 
}