package com.danbro.order.rpc.clients;

import java.util.List;
import com.danbro.common.entity.ResultBean;
import com.danbro.order.controller.esModel.ProductSkuInfoEsModel;
import com.danbro.order.rpc.fallbacks.SearchFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Danrbo
 * @Classname SearchClient
 * @Description TODO
 * @Date 2021/2/16 20:36
 */
@Component
@FeignClient(value = "service-search", fallback = SearchFallback.class)
public interface SearchClient {
    @PostMapping("search/product")
    ResultBean<?> batchInsert(@RequestBody List<ProductSkuInfoEsModel> skuInfoEsModels);
}
