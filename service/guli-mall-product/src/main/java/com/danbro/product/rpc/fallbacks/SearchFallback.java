package com.danbro.product.rpc.fallbacks;

import java.util.List;
import com.danbro.common.entity.ResultBean;
import com.danbro.common.enums.ResponseCode;
import com.danbro.product.controller.esModel.ProductSkuInfoEsModel;
import com.danbro.product.rpc.clients.SearchClient;
import org.springframework.stereotype.Component;

/**
 * @author Danrbo
 * @Classname SearchFallback
 * @Description TODO
 * @Date 2021/2/16 20:36
 */
@Component
public class SearchFallback implements SearchClient {

    @Override
    public ResultBean<?> batchInsert(List<ProductSkuInfoEsModel> skuInfoEsModels) {
        return ResultBean.ofSuccess(ResponseCode.RPC_TIME_OUT);
    }
}