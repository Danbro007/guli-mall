package com.danbro.search.controller;

import java.util.List;
import javax.annotation.Resource;
import com.danbro.common.entity.ResultBean;
import com.danbro.search.controller.esModel.ProductSkuInfoEsModel;
import com.danbro.search.service.impl.SearchService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname SearchController
 * @Description TODO
 * @Date 2021/2/17 12:16
 * @Created by Administrator
 */
@RestController
public class SearchController {
    @Resource
    SearchService searchService;

    @ApiOperation("给sku添加到ES里")
    @PostMapping("search/product")
    public ResultBean<?> productOnSale(@RequestBody List<ProductSkuInfoEsModel> productSkuInfoEsModels) {
        searchService.productBatchOnSale(productSkuInfoEsModels);
        return ResultBean.ofSuccess();
    }

}
