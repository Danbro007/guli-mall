package com.danbro.product.rpc.fallbacks;

import java.util.List;
import com.danbro.common.entity.ResultBean;
import com.danbro.common.enums.ResponseCode;
import com.danbro.product.controller.vo.SmsMemberPriceVo;
import com.danbro.product.rpc.clients.SmsMemberPriceClient;
import org.springframework.stereotype.Component;

/**
 * @Classname SmsSpuBondFallback
 * @Description TODO
 * @Date 2021/2/11 22:22
 * @Created by Administrator
 */
@Component
public class SmsMemberPriceFallback implements SmsMemberPriceClient {
    @Override
    public ResultBean<SmsMemberPriceVo> insertMemberPrice(SmsMemberPriceVo smsMemberPriceVo) {
        return ResultBean.ofFailure(ResponseCode.RPC_TIME_OUT);
    }

    @Override
    public ResultBean<List<SmsMemberPriceVo>> batchInsertMemberPrice(List<SmsMemberPriceVo> memberPriceVoList) {
        return ResultBean.ofFailure(ResponseCode.RPC_TIME_OUT);
    }
}
