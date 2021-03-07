package com.danbro.order.rpc.fallbacks;

import com.danbro.common.entity.ResultBean;
import com.danbro.common.enums.ResponseCode;
import com.danbro.order.controller.vo.SmsSpuBondsVo;
import com.danbro.order.rpc.clients.SmsSpuBondsClient;
import org.springframework.stereotype.Component;

/**
 * @Classname SmsSpuBondFallback
 * @Description TODO
 * @Date 2021/2/11 22:22
 * @Created by Administrator
 */
@Component
public class SmsSpuBondFallback implements SmsSpuBondsClient {
    @Override
    public ResultBean<SmsSpuBondsVo> insertSpuBonds(SmsSpuBondsVo smsSpuBondsVo) {
        return ResultBean.ofFailure(ResponseCode.RPC_TIME_OUT);
    }
}
