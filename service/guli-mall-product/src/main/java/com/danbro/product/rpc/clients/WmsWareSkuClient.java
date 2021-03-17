package com.danbro.product.rpc.clients;

import com.danbro.common.entity.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Danrbo
 * @Classname WmsWareSkuClient
 * @Description TODO 查询sku库存的客户端
 * @Date 2021/2/16 19:47
 */
@Component
@FeignClient(name = "service-wms")
public interface WmsWareSkuClient {
    @GetMapping("ware/waresku/hasStock/{skuId}")
    ResultBean<Boolean> hasStock(@PathVariable Long skuId);
}
