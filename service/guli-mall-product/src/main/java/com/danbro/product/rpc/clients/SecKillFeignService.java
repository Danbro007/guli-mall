package com.danbro.product.rpc.clients;

import com.danbro.common.entity.ResultBean;
import com.danbro.product.controller.vo.front.SmsSeckillSkuRelationVo;
import com.danbro.product.rpc.clients.fallbacks.SecKillFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Danrbo
 * @Classname SmsFeignService
 * @Description TODO 秒杀远程调用客户端
 * @Date 2021/3/17 14:14
 */
@FeignClient(value = "service-seckill", fallback = SecKillFallback.class)
public interface SecKillFeignService {
    @GetMapping("seckill/info/{skuId}")
    ResultBean<SmsSeckillSkuRelationVo> getSecKillRelationInfo(@PathVariable Long skuId);
}
