package com.danbro.search;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Resource;
import com.danbro.search.entity.Account;
import com.danbro.search.repositories.AccountRepository;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
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

    @Resource
    AccountRepository accountRepository;

    @Test
    public void add() {
    }

    @Test
    public void query() {
        Iterable<Account> iterable = accountRepository.findAll();
        iterable.forEach(System.out::println);
    }

    @Test
    public void queryById() {
        Account account = elasticsearchRestTemplate.queryForObject(GetQuery.getById("1"), Account.class);
        System.out.println(account);
    }

    @Test
    public void queryByCondition() {
        List<Account> accounts = accountRepository.findByBalanceBefore(5000L);
        System.out.println(accounts);
    }

    @Test
    public void baseQuery() {
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("lastname", "Duke");
        Iterable<Account> accountIterable = accountRepository.search(matchQueryBuilder);
        accountIterable.forEach(System.out::println);
    }

    @Test
    public void Query() {
        // 查询年龄在 30~50
        Criteria criteria = new Criteria("age").between(30L, 50L);
        Query query = new CriteriaQuery(criteria);
        SearchHits<Account> accountSearchHits = elasticsearchRestTemplate.search(query, Account.class);
        Objects.requireNonNull(accountSearchHits.getSearchHits()).forEach(System.out::println);
    }

    @Test
    public void aggQuery1() {
        // 查询地址有 mill 的数据
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("address", "mill");
        // 查询所有数据然后聚合出平均年龄和年龄分布
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(matchQueryBuilder);
        // 年龄分布
        queryBuilder.addAggregation(AggregationBuilders.terms("ageAgg").size(10).field("age"));
        // 平均年龄
        queryBuilder.addAggregation(AggregationBuilders.avg("aggAvg").field("age"));
        // 平均薪资
        queryBuilder.addAggregation(AggregationBuilders.avg("balanceAgg").field("balance"));
        SearchHits<Account> searchHits = elasticsearchRestTemplate.search(queryBuilder.build(), Account.class);
        Aggregations aggregations = searchHits.getAggregations();
        Map<String, Aggregation> asMap = aggregations.getAsMap();
    }

    @Test
    public void aggQuery2() {
        // 查询所有数据然后聚合每个年龄的平均工资
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 子查询 聚合出每个年龄的平均工资
        AvgAggregationBuilder avgAggregationBuilder = AggregationBuilders.avg("balanceAvg").field("balance");
        //
        queryBuilder.addAggregation(AggregationBuilders.terms("ageAgg").size(10).field("age").subAggregation(avgAggregationBuilder));
        // 平均薪资
        SearchHits<Account> searchHits = elasticsearchRestTemplate.search(queryBuilder.build(), Account.class);
        Aggregations aggregations = searchHits.getAggregations();
        Map<String, Aggregation> asMap = aggregations.getAsMap();
    }

}

