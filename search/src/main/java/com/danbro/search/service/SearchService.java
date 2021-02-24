package com.danbro.search.service;


import java.util.List;

import com.danbro.search.controller.esModel.ProductSkuInfoEsModel;
import com.danbro.search.controller.vo.SearchParamVo;
import com.danbro.search.controller.vo.SearchResponseVo;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;

public interface SearchService {

    /**
     * 把商品批量加入到ES里
     *
     * @param productAttrEsModels 商品数据
     */
    void productBatchOnSale(List<ProductSkuInfoEsModel> productAttrEsModels);

    /**
     * 商品的查询检索
     *
     * @param searchParamVo 查询条件
     * @return
     */
    SearchResponseVo search(SearchParamVo searchParamVo, HttpServletRequest request);
}
