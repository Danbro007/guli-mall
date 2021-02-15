package com.danbro.ware.rpc.fallbacks;

import com.danbro.common.entity.ResultBean;
import com.danbro.common.enums.ResponseCode;
import com.danbro.ware.controller.vo.PmsSkuInfoVo;
import com.danbro.ware.rpc.clients.PmsSkuInfoClient;
import org.springframework.stereotype.Component;

/**
 * @Classname PmsSkuInfoFallback
 * @Description TODO
 * @Date 2021/2/15 10:47
 * @Created by Administrator
 */
@Component
public class PmsSkuInfoFallback implements PmsSkuInfoClient {
    @Override
    public ResultBean<PmsSkuInfoVo> getSkuInfo(Long skuId) {
        return ResultBean.ofFailure(ResponseCode.RPC_TIME_OUT);
    }
}
