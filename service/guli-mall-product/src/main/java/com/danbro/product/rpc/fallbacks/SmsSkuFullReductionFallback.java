package com.danbro.product.rpc.fallbacks;

import com.danbro.common.entity.ResultBean;
import com.danbro.common.enums.ResponseCode;
import com.danbro.product.controller.vo.SmsSkuFullReductionVo;
import com.danbro.product.rpc.clients.SmsSkuFullReductionClient;
import org.springframework.stereotype.Component;

/**
 * @Classname SmsSkuFullReductionFallback
 * @Description TODO
 * @Date 2021/2/12 17:36
 * @Created by Administrator
 */
@Component
public class SmsSkuFullReductionFallback implements SmsSkuFullReductionClient {
    @Override
    public ResultBean<SmsSkuFullReductionVo> insertSkuFullReduction(SmsSkuFullReductionVo smsSkuFullReductionVo) {
        return ResultBean.ofFailure(ResponseCode.RPC_TIME_OUT);
    }
}
