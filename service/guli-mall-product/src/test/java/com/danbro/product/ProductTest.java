package com.danbro.product;


import java.util.List;
import com.danbro.product.controller.vo.PmsAttrAttrgroupRelationVo;
import com.danbro.product.controller.vo.PmsAttrBaseInfoVo;
import com.danbro.product.service.PmsAttrAttrgroupRelationService;
import com.danbro.product.service.PmsAttrGroupService;
import com.danbro.product.service.PmsAttrService;
import com.danbro.product.service.PmsCategoryBrandRelationService;
import com.danbro.product.service.PmsCategoryService;
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
@SpringBootTest
public class ProductTest {
    @Autowired
    PmsCategoryService pmsCategoryService;
    @Autowired
    PmsCategoryBrandRelationService pmsCategoryBrandRelationService;
    @Autowired
    PmsAttrAttrgroupRelationService pmsAttrAttrgroupRelationService;
    @Autowired
    PmsAttrGroupService attrGroupService;
    @Autowired
    PmsAttrService attrService;

    @Test
    public void test1() {
        Long[] catIds = new Long[]{500L,333L};
        pmsCategoryBrandRelationService.batchDeleteByCategoryId(catIds);
    }

    @Test
    public void test2() {
        attrService.batchDeleteAttr(new Long[]{11L});
    }
    @Test
    public void test3() {
    }



}
