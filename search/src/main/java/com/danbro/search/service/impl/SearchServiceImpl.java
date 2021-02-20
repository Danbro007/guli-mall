package com.danbro.search.service.impl;

import java.util.List;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.exceptions.GuliMallException;
import com.danbro.common.utils.MyObjectUtils;
import com.danbro.common.utils.MyStrUtils;
import com.danbro.search.controller.esModel.ProductSkuInfoEsModel;
import com.danbro.search.controller.vo.SearchParamVo;
import com.danbro.search.controller.vo.SearchResponseVo;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

/**
 * @Classname SearchServiceImpl
 * @Description TODO
 * @Date 2021/2/17 12:20
 * @Created by Administrator
 */
@Service
public class SearchServiceImpl implements SearchService, InitializingBean {
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

    @Override
    public SearchResponseVo search(SearchParamVo searchParamVo) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (MyStrUtils.isNotEmpty(searchParamVo.getKeyword())) {
            MatchQueryBuilder skuTitle = QueryBuilders.matchQuery("skuTitle", searchParamVo.getKeyword());
            boolQueryBuilder.must().add(skuTitle);
        }
        if (MyObjectUtils.isNotNull(searchParamVo.getBrandId())){
            TermQueryBuilder brandId = QueryBuilders.termQuery("brandId", searchParamVo.getBrandId());
            boolQueryBuilder.filter().add(brandId);
        }
        if (MyObjectUtils.isNotNull(searchParamVo.getCatalog3Id())){
            TermQueryBuilder catalogId = QueryBuilders.termQuery("catalogId", searchParamVo.getCatalog3Id());
            boolQueryBuilder.filter().add(catalogId);
        }
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(boolQueryBuilder);
        elasticsearchRestTemplate.search(queryBuilder.build(),ProductSkuInfoEsModel.class);
        return new SearchResponseVo();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(ProductSkuInfoEsModel.class);
        if (!indexOperations.exists()) {
            boolean b = indexOperations.create();
            if (b) {
                Document mapping = indexOperations.createMapping(ProductSkuInfoEsModel.class);
                indexOperations.putMapping(mapping);
            }
        }
    }
}
