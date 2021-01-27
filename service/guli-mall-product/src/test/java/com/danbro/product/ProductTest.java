package com.danbro.product;

import com.danbro.product.entity.PmsCategoryBrandRelation;
import com.danbro.product.mapper.PmsCategoryBrandRelationMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Classname Test
 * @Description TODO
 * @Date 2021/1/27 21:42
 * @Created by Administrator
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class ProductTest {
    @Autowired
    PmsCategoryBrandRelationMapper relationMapper;

    @Test
    public void test1(){
        PmsCategoryBrandRelation pmsCategoryBrandRelation = new PmsCategoryBrandRelation();
        pmsCategoryBrandRelation.setCatelogName("123");
        relationMapper.insert(pmsCategoryBrandRelation);
    }
}
