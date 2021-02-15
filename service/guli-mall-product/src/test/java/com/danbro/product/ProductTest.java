package com.danbro.product;


import cn.hutool.core.util.StrUtil;
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
        Long[] catIds = new Long[]{500L, 333L};
        pmsCategoryBrandRelationService.batchDeleteByCategoryId(catIds);
    }

    @Test
    public void test2() {
        attrService.batchDeleteAttr(new Long[]{11L});
    }

    @Test
    public void test3() {
        String imageUrl = "https://danbro-mall.oss-cn-shanghai.aliyuncs.com/2021/02/12/b8e141a8-bfdf-468b-8691-cba5e66d9215_0d40c24b264aa511.jpg";
        String[] split = StrUtil.split(imageUrl, "_");
        System.out.println(split[split.length-1]);

    }
}
