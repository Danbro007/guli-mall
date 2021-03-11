package com.danbro.ware.service.impl;

import com.danbro.common.entity.ResultBean;
import com.danbro.ware.controller.vo.OmsOrderVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Danrbo
 * @Classname OmsFeignService
 * @Description TODO
 * @Date 2021/3/11 13:53
 */
@FeignClient(value = "service-oms")
public interface OmsFeignService {
    @GetMapping("order/info/{orderSn}")
    ResultBean<OmsOrderVo> getOrderInfoByOrderSn(@PathVariable String orderSn);
}
