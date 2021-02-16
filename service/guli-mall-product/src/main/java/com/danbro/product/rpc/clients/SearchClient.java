package com.danbro.product.rpc.clients;

import com.danbro.common.entity.ResultBean;
import com.danbro.product.controller.esModel.ProductAttrEsModel;
import com.danbro.product.controller.esModel.ProductSkuInfoEsModel;
import com.danbro.product.rpc.fallbacks.SearchFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Danrbo
 * @Classname SearchClient
 * @Description TODO
 * @Date 2021/2/16 20:36
 */
@Component
@FeignClient(value = "service-search", fallback = SearchFallback.class)
public interface SearchClient {
    @PostMapping("search")
    ResultBean<?> batchInsert(@RequestBody List<ProductSkuInfoEsModel> skuInfoEsModels);
}
