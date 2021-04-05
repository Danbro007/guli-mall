package com.danbro.product.rpc.clients.fallbacks;

import com.danbro.common.entity.ResultBean;
import com.danbro.common.enums.ResponseCode;
import com.danbro.product.controller.vo.front.SmsSeckillSkuRelationVo;
import com.danbro.product.rpc.clients.SecKillFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Classname SecKillFallbackN
 * @Description TODO
 * @Date 2021/4/5 20:53
 * @Created by Administrator
 */
@Slf4j
@Component
public class SecKillFallback implements SecKillFeignService {
    @Override
    public ResultBean<SmsSeckillSkuRelationVo> getSecKillRelationInfo(Long skuId) {
        System.out.println("调用熔断方法。。。。。。。。。。。。。。。。。。。。");
        return ResultBean.ofFailure(ResponseCode.RPC_TIME_OUT);
    }
}
