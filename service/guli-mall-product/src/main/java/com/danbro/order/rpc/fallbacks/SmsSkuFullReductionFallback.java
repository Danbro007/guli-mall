package com.danbro.order.rpc.fallbacks;

import com.danbro.common.entity.ResultBean;
import com.danbro.common.enums.ResponseCode;
import com.danbro.order.controller.vo.SmsSkuFullReductionVo;
import com.danbro.order.rpc.clients.SmsSkuFullReductionClient;
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
