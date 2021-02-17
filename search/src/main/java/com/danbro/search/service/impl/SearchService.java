package com.danbro.search.service.impl;


import java.util.List;
import com.danbro.search.controller.esModel.ProductSkuInfoEsModel;

public interface SearchService {

    /**
     * 把商品批量加入到ES里
     *
     * @param productAttrEsModels 商品数据
     */
    void productBatchOnSale(List<ProductSkuInfoEsModel> productAttrEsModels);
}
