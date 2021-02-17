package com.danbro.search.service.impl;

import java.util.List;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.exceptions.GuliMallException;
import com.danbro.search.controller.esModel.ProductSkuInfoEsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

/**
 * @Classname SearchServiceImpl
 * @Description TODO
 * @Date 2021/2/17 12:20
 * @Created by Administrator
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public void productBatchOnSale(List<ProductSkuInfoEsModel> productSkuInfoEsModels) {
        try {
            elasticsearchRestTemplate.save(productSkuInfoEsModels);
        } catch (Exception exception) {
            throw new GuliMallException(ResponseCode.PRODUCT_UP_EXCEPTION);
        }
    }
}
