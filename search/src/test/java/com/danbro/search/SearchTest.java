package com.danbro.search;

import java.util.ArrayList;
import java.util.List;
import com.danbro.common.utils.MyCollectionUtils;
import com.danbro.common.utils.MyObjectUtils;
import com.danbro.common.utils.MyStrUtils;
import com.danbro.search.controller.esModel.ProductSkuInfoEsModel;
import com.danbro.search.controller.vo.SearchParamVo;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Classname Test
 * @Description TODO
 * @Date 2021/2/15 16:21
 * @Created by Administrator
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchApplication.class)
public class SearchTest {
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;


    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;


    @Test
    public void test() {
        // 创建索引
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(ProductSkuInfoEsModel.class);
        indexOperations.create();
        // 创建映射
        Document mapping = indexOperations.createMapping(ProductSkuInfoEsModel.class);
        indexOperations.putMapping(mapping);
    }

    @Test
    public void query3() {
        SearchParamVo searchParamVo = new SearchParamVo();
        List<Long> brandIds = new ArrayList<>();
//        searchParamVo.setKeyword("华为");
        brandIds.add(5L);
        brandIds.add(3L);
        searchParamVo.setSort("price_desc");
        List<String> attrs = new ArrayList<>();
        attrs.add("7_骁龙888:麒麟990");
        searchParamVo.setAttrs(attrs);
        brandIds.add(2L);
        searchParamVo.setBrandId(brandIds);
        searchParamVo.setCatalogId(225L);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        // 关键字查找
        if (MyStrUtils.isNotEmpty(searchParamVo.getKeyword())) {
            boolQueryBuilder.must(new MatchQueryBuilder("skuTitle", searchParamVo.getKeyword()));
        }
        // brandId terms
        if (MyObjectUtils.isNotNull(searchParamVo.getBrandId())) {
            boolQueryBuilder.should(new TermsQueryBuilder("brandId", searchParamVo.getBrandId()));
        }
        // 三级分类ID
        if (MyObjectUtils.isNotNull(searchParamVo.getCatalogId())) {
            boolQueryBuilder.must(new TermQueryBuilder("catalogId", searchParamVo.getCatalogId()));
        }

        // 排序规则
        if (MyStrUtils.isNotEmpty(searchParamVo.getSort())) {
            String sort = searchParamVo.getSort();
            List<String> stringList = MyStrUtils.split(sort, '_');
            String fieldName = stringList.get(0);
            String orderType = stringList.get(1);
            FieldSortBuilder order = new FieldSortBuilder(fieldName).order("ASC".equals(orderType) ? SortOrder.ASC : SortOrder.DESC);
            nativeSearchQueryBuilder.withSort(order);
        }
        // 是否有库存
        if (MyObjectUtils.isNotNull(searchParamVo.getHasStock())) {
            boolQueryBuilder.must(new TermQueryBuilder("hasStock", searchParamVo.getHasStock()));
        }
        // 是否有指定的属性及属性对应的属性值
        if (MyCollectionUtils.isNotEmpty(searchParamVo.getAttrs())) {
            BoolQueryBuilder nestedBoolQueryBuilder = QueryBuilders.boolQuery();
            searchParamVo.getAttrs().forEach(attr -> {
                List<String> split = MyStrUtils.split(attr, '_');
                String attrId = split.get(0);
                List<String> valueList = MyStrUtils.split(split.get(1), ':');
                nestedBoolQueryBuilder.must(QueryBuilders.termQuery("attrs.attrId", attrId));
                nestedBoolQueryBuilder.must(QueryBuilders.termsQuery("attrs.attrValue", valueList));
            });
            NestedQueryBuilder nestedQueryBuilder = QueryBuilders.nestedQuery("attrs", nestedBoolQueryBuilder, ScoreMode.None);
            boolQueryBuilder.must(nestedQueryBuilder);
        }
        nativeSearchQueryBuilder.withQuery(boolQueryBuilder);
        SearchHits<ProductSkuInfoEsModel> search = elasticsearchRestTemplate.search(nativeSearchQueryBuilder.build(), ProductSkuInfoEsModel.class);
        System.out.println("1223");
    }

    @Test
    public void test1() {
        List<String> split = MyStrUtils.split("_200", '_');
        System.out.println(split.size());
        System.out.println(split);
    }
}

