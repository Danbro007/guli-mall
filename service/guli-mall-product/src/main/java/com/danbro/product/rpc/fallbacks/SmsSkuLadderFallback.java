package com.danbro.product.rpc.fallbacks;

import com.danbro.common.entity.ResultBean;
import com.danbro.common.enums.ResponseCode;
import com.danbro.product.controller.vo.SmsSkuLadderVo;
import com.danbro.product.rpc.clients.SmsSkuLadderClient;
import org.springframework.stereotype.Component;

/**
 * @Classname SmsSkuLadderFallback
 * @Description TODO
 * @Date 2021/2/12 17:07
 * @Created by Administrator
 */
@Component
public class SmsSkuLadderFallback implements SmsSkuLadderClient {
    @Override
    public ResultBean<SmsSkuLadderVo> insertSkuLadder(SmsSkuLadderVo smsSpuBondsVo) {
        return ResultBean.ofFailure(ResponseCode.RPC_TIME_OUT);
    }
}
