package com.danbro.product.rpc.fallbacks;

import com.danbro.common.entity.ResultBean;
import com.danbro.common.enums.ResponseCode;
import com.danbro.product.rpc.clients.WmsWareSkuClient;
import org.springframework.stereotype.Component;

/**
 * @author Danrbo
 * @Classname WmsWareSkuFallback
 * @Description TODO
 * @Date 2021/2/16 19:48
 */
@Component
public class WmsWareSkuFallback implements WmsWareSkuClient {
    @Override
    public ResultBean<Boolean> hasStock(Long skuId) {
        return ResultBean.ofFailure(ResponseCode.RPC_TIME_OUT);
    }
}
