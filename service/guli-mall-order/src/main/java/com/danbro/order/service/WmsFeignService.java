package com.danbro.order.service;

import com.danbro.common.entity.ResultBean;
import com.danbro.order.controller.vo.FareVo;
import com.danbro.order.controller.vo.WmsLockStockResultVo;
import com.danbro.order.entity.OmsOrderItem;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Danrbo
 * @Classname UmsFeignService
 * @Description TODO
 * @Date 2021/3/10 11:28
 */
@FeignClient(value = "service-wms")
public interface WmsFeignService {
    @ApiOperation("获取邮费信息和寄送地址")
    @GetMapping("ware/wareinfo/fare/{addressId}")
    ResultBean<FareVo> getFare(@PathVariable Long addressId);


    @ApiOperation("批量锁商品的库存")
    @PostMapping("ware/waresku/lockStock")
    ResultBean<List<WmsLockStockResultVo>> lockStock(@RequestBody List<OmsOrderItem> items);
}
