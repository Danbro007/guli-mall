package com.danbro.search.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.exceptions.GuliMallException;
import com.danbro.common.utils.MyCollectionUtils;
import com.danbro.common.utils.MyObjectUtils;
import com.danbro.common.utils.MyStrUtils;
import com.danbro.search.controller.esModel.ProductSkuInfoEsModel;
import com.danbro.search.controller.vo.SearchParamVo;
import com.danbro.search.controller.vo.SearchResponseVo;
import com.danbro.search.service.SearchService;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.nested.ParsedNested;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
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

    private static final char SPLIT_CHAR = '_';
    private static final char VALUE_SPLIT_CHAR = ':';
    /**
     * 每页显示的数据数
     */
    private static final Integer DEFAULT_PAGE_SIZE = 50;
    /**
     * 默认的页数
     */
    private static final Integer DEFAULT_PAGE_NUM = 0;


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
        SearchHits<ProductSkuInfoEsModel> searchHits = processSearchParam(searchParamVo);
        return buildSearchResponse(searchHits, searchParamVo);
    }

    /**
     * 封装检索结果
     *
     * @param searchHits 检索结果
     * @return 响应结果
     */
    private SearchResponseVo buildSearchResponse(SearchHits<ProductSkuInfoEsModel> searchHits, SearchParamVo searchParamVo) {
        SearchResponseVo searchResponseVo = new SearchResponseVo();
        // 封装总检索的商品数
        searchResponseVo.setTotal(searchHits.getTotalHits());
        if (MyObjectUtils.isNotNull(searchParamVo.getPageNum()) && searchParamVo.getPageNum() > 1) {
            searchResponseVo.setPageNum(searchParamVo.getPageNum());
        } else {
            searchResponseVo.setPageNum(1);
        }
        // 计算出总页数
        long totalPageNum = searchResponseVo.getTotal() % DEFAULT_PAGE_SIZE == 0 ? searchResponseVo.getTotal() / DEFAULT_PAGE_SIZE : searchResponseVo.getTotal() / DEFAULT_PAGE_SIZE + 1;
        searchResponseVo.setTotalPages((int) totalPageNum);
        // 封装查询的到商品
        searchResponseVo.setProducts(searchHits.getSearchHits().stream().map(searchHit -> {
            ProductSkuInfoEsModel skuInfoEsModel = searchHit.getContent();
            // 关键字高亮
            if (MyStrUtils.isNotEmpty(searchParamVo.getKeyword())) {
                List<String> skuTitle = searchHit.getHighlightFields().get("skuTitle");
                skuInfoEsModel.setSkuTitle(skuTitle.get(0));
            }
            return skuInfoEsModel;
        }).collect(Collectors.toList()));
        if (MyObjectUtils.isNotNull(searchHits.getAggregations())) {
            Aggregations aggregations = searchHits.getAggregations();
            // 封装品牌聚合信息
            List<SearchResponseVo.BrandVo> brandVos = buildBrandVos(aggregations);
            searchResponseVo.setBrandVos(brandVos);
            // 封装三级分类聚合信息
            List<SearchResponseVo.CatalogVo> catalogVos = buildCatalogVos(aggregations);
            searchResponseVo.setCatalogVos(catalogVos);
            // 封装属性聚合消息
            List<SearchResponseVo.AttrVo> attrVos = buildAttrVos(aggregations);
            searchResponseVo.setAttrs(attrVos);
        }
        // 生成导航页
        List<Integer> pageNavs = new ArrayList<>();
        for (Integer i = 0; i < searchResponseVo.getTotalPages(); i++) {
            pageNavs.add(i);
        }
        searchResponseVo.setPageNavs(pageNavs);
        return searchResponseVo;
    }


    /**
     * 封装属性和属性值的聚合信息
     *
     * @param aggregations 检索获取的聚合信息
     * @return 聚合结果
     */
    private List<SearchResponseVo.AttrVo> buildAttrVos(Aggregations aggregations) {
        ArrayList<SearchResponseVo.AttrVo> attrVos = new ArrayList<>();
        ParsedNested attrsAgg = aggregations.get("attrs_agg");
        Aggregations subAgg = attrsAgg.getAggregations();
        ParsedLongTerms attrIdAgg = subAgg.get("attrId_agg");
        attrIdAgg.getBuckets().forEach(bucket -> {
            SearchResponseVo.AttrVo attrVo = new SearchResponseVo.AttrVo();
            // 属性ID
            String attrId = bucket.getKeyAsString();
            // 属性名
            ParsedStringTerms attrNameAgg = bucket.getAggregations().get("attrName_agg");
            String attrName = attrNameAgg.getBuckets().get(0).getKeyAsString();
            attrVo.setAttrId(Long.parseLong(attrId));
            attrVo.setAttrName(attrName);
            // 属性值
            ParsedStringTerms attrValueAgg = bucket.getAggregations().get("attrValue_agg");
            List<String> valueList = new ArrayList<>();
            for (Terms.Bucket valueAggBucket : attrValueAgg.getBuckets()) {
                String value = valueAggBucket.getKeyAsString();
                valueList.add(value);
            }
            attrVo.setAttrValues(valueList);
            attrVos.add(attrVo);
        });
        return attrVos;
    }

    /**
     * 构建三级分类聚合结果
     *
     * @param aggregations 聚合信息
     * @return 聚合后的结果
     */
    private List<SearchResponseVo.CatalogVo> buildCatalogVos(Aggregations aggregations) {
        ArrayList<SearchResponseVo.CatalogVo> catalogVos = new ArrayList<>();
        ParsedLongTerms catalogAgg = aggregations.get("catalog_agg");
        catalogAgg.getBuckets().forEach(bucket -> {
            SearchResponseVo.CatalogVo catalogVo = new SearchResponseVo.CatalogVo();
            // 三级分类ID
            String catalogId = bucket.getKeyAsString();
            catalogVo.setCatalogId(Long.parseLong(catalogId));
            Aggregations subAgg = bucket.getAggregations();
            // 三级分类名
            ParsedStringTerms catalogNameAgg = subAgg.get("catalogName_agg");
            String catalogName = catalogNameAgg.getBuckets().get(0).getKeyAsString();
            catalogVo.setCatalogName(catalogName);
            catalogVos.add(catalogVo);
        });
        return catalogVos;
    }

    /**
     * 封装品牌聚合信息
     *
     * @param aggregations 聚合信息
     * @return 封装后的聚合结果
     */
    private List<SearchResponseVo.BrandVo> buildBrandVos(Aggregations aggregations) {
        // 封装聚合的品牌信息
        List<SearchResponseVo.BrandVo> brandVos = new ArrayList<>();
        ParsedLongTerms brandAgg = aggregations.get("brand_agg");
        for (Terms.Bucket bucket : brandAgg.getBuckets()) {
            // 品牌ID
            SearchResponseVo.BrandVo brandVo = new SearchResponseVo.BrandVo().setBrandId(Long.parseLong(bucket.getKeyAsString()));
            // 子聚合 获取品牌的名字和logo
            Aggregations subAgg = bucket.getAggregations();
            // 品牌名
            ParsedStringTerms brandNameAgg = subAgg.get("brandName_agg");
            String name = brandNameAgg.getBuckets().get(0).getKeyAsString();
            ParsedStringTerms brandImgAgg = subAgg.get("brandImg_agg");
            // 品牌Logo
            String brandImgAggName = brandImgAgg.getBuckets().get(0).getKeyAsString();
            brandVo.setBrandImg(brandImgAggName).setBrandName(name);
            brandVos.add(brandVo);
        }
        return brandVos;
    }


    /**
     * 负责检索条件的处理和查询
     *
     * @param searchParamVo 检索条件
     * @return 查询结果
     */
    private SearchHits<ProductSkuInfoEsModel> processSearchParam(SearchParamVo searchParamVo) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 关键字
        if (MyStrUtils.isNotEmpty(searchParamVo.getKeyword())) {
            // 关键字的高亮设置
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("skuTitle");
            highlightBuilder.preTags("<b style='color:red'>");
            highlightBuilder.postTags("</b>");
            nativeSearchQueryBuilder.withHighlightBuilder(highlightBuilder);
            boolQueryBuilder.must().add(QueryBuilders.matchQuery("skuTitle", searchParamVo.getKeyword()));
        }
        // 品牌ID
        if (MyCollectionUtils.isNotEmpty(searchParamVo.getBrandId())) {
            boolQueryBuilder.filter().add(QueryBuilders.termsQuery("brandId", searchParamVo.getBrandId()));
        }
        // 三级分类ID
        if (MyObjectUtils.isNotNull(searchParamVo.getCatalogId())) {
            boolQueryBuilder.filter().add(QueryBuilders.termQuery("catalogId", searchParamVo.getCatalogId()));
        }
        // 排序规则 例如：hotScore_desc
        if (MyStrUtils.isNotEmpty(searchParamVo.getSort())) {
            String sort = searchParamVo.getSort();
            List<String> sortStringList = MyStrUtils.split(sort, SPLIT_CHAR);
            // 排序的字段
            String fieldName = sortStringList.get(0);
            // 升序还是降序
            String orderType = sortStringList.get(1);
            FieldSortBuilder order = new FieldSortBuilder(fieldName).order(MyStrUtils.equalsIgnoreCase("asc", orderType) ? SortOrder.ASC : SortOrder.DESC);
            nativeSearchQueryBuilder.withSort(order);
        }
        // 是否有库存
        if (MyObjectUtils.isNotNull(searchParamVo.getHasStock())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("hasStock", searchParamVo.getHasStock()));
        }
        // 价格区间 1_500 or 1_ or _500
        if (MyStrUtils.isNotEmpty(searchParamVo.getSkuPrice())) {
            String skuPrice = searchParamVo.getSkuPrice();
            // _500
            if (MyStrUtils.startWith(skuPrice, SPLIT_CHAR)) {
                String highPrice = MyStrUtils.split(skuPrice, SPLIT_CHAR).get(1);
                boolQueryBuilder.filter().add(QueryBuilders.rangeQuery("skuPrice").lte(highPrice));
            }
            // 1_
            else if (MyStrUtils.endWith(skuPrice, SPLIT_CHAR)) {
                String lowPrice = MyStrUtils.split(skuPrice, SPLIT_CHAR).get(0);
                boolQueryBuilder.filter().add(QueryBuilders.rangeQuery("skuPrice").gte(lowPrice));
            } else {
                List<String> priceStringList = MyStrUtils.split(skuPrice, SPLIT_CHAR);
                String lowPrice = priceStringList.get(0);
                String highPrice = priceStringList.get(1);
                boolQueryBuilder.filter().add(QueryBuilders.rangeQuery("skuPrice").gte(lowPrice).lte(highPrice));
            }
        }
        // attrs 属性 例如 1_5寸:8寸
        if (MyCollectionUtils.isNotEmpty(searchParamVo.getAttrs())) {
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
            for (String attr : searchParamVo.getAttrs()) {
                List<String> stringList = MyStrUtils.split(attr, SPLIT_CHAR);
                // 属性ID
                String attrId = stringList.get(0);
                // 属性值
                String values = stringList.get(1);
                // 只有至少两个属性值 6寸:8寸
                if (MyStrUtils.contains(values, VALUE_SPLIT_CHAR)) {
                    List<String> valueList = MyStrUtils.split(values, VALUE_SPLIT_CHAR);
                    boolQuery.must(QueryBuilders.termsQuery("attrs.attrValue", valueList));

                } else {
                    boolQuery.must(QueryBuilders.termsQuery("attrs.attrValue", values));
                }
                boolQuery.must(QueryBuilders.termQuery("attrs.attrId", attrId));
            }
            NestedQueryBuilder nestedQuery = QueryBuilders.nestedQuery("attrs", boolQuery, ScoreMode.None);
            boolQueryBuilder.filter(nestedQuery);
        }
        // 分页
        PageRequest pageRequest;
        if (MyObjectUtils.isNotNull(searchParamVo.getPageNum()) && searchParamVo.getPageNum() > 0) {
            pageRequest = PageRequest.of(searchParamVo.getPageNum() - 1, DEFAULT_PAGE_SIZE);
        } else {
            pageRequest = PageRequest.of(DEFAULT_PAGE_NUM, DEFAULT_PAGE_SIZE);
        }
        nativeSearchQueryBuilder.withPageable(pageRequest);
        // 合并查询条件
        nativeSearchQueryBuilder.withQuery(boolQueryBuilder);
        // 品牌聚合
        TermsAggregationBuilder brandAgg = AggregationBuilders.terms("brand_agg").field("brandId").size(DEFAULT_PAGE_SIZE);
        brandAgg.subAggregation(AggregationBuilders.terms("brandName_agg").field("brandName").size(DEFAULT_PAGE_SIZE));
        brandAgg.subAggregation(AggregationBuilders.terms("brandImg_agg").field("brandImg").size(DEFAULT_PAGE_SIZE));
        nativeSearchQueryBuilder.addAggregation(brandAgg);
        // 三级分类聚合
        TermsAggregationBuilder catalogIdAgg = AggregationBuilders.terms("catalog_agg").field("catalogId").size(DEFAULT_PAGE_SIZE);
        catalogIdAgg.subAggregation(AggregationBuilders.terms("catalogName_agg").field("catalogName").size(DEFAULT_PAGE_SIZE));
        nativeSearchQueryBuilder.addAggregation(catalogIdAgg);
        // 属性聚合
        NestedAggregationBuilder attrsAgg = AggregationBuilders.nested("attrs_agg", "attrs");
        TermsAggregationBuilder attrIdAgg = AggregationBuilders.terms("attrId_agg").field("attrs.attrId").size(DEFAULT_PAGE_SIZE);
        attrIdAgg.subAggregation(AggregationBuilders.terms("attrName_agg").field("attrs.attrName").size(DEFAULT_PAGE_SIZE));
        attrIdAgg.subAggregation(AggregationBuilders.terms("attrValue_agg").field("attrs.attrValue").size(DEFAULT_PAGE_SIZE));
        attrsAgg.subAggregation(attrIdAgg);
        nativeSearchQueryBuilder.addAggregation(attrsAgg);
        return elasticsearchRestTemplate.search(nativeSearchQueryBuilder.build(), ProductSkuInfoEsModel.class);
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
