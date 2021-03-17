package com.danbro.product.rpc.clients;

import com.danbro.common.entity.ResultBean;
import com.danbro.product.controller.esModel.ProductSkuInfoEsModel;
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
@FeignClient(value = "service-search")
public interface SearchClient {
    @PostMapping("search/product")
    ResultBean<?> batchInsert(@RequestBody List<ProductSkuInfoEsModel> skuInfoEsModels);
}
