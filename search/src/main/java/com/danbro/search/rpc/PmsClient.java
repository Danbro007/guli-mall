package com.danbro.search.rpc;


import com.danbro.common.entity.ResultBean;
import com.danbro.search.controller.vo.PmsAttrDetailVo;
import com.danbro.search.controller.vo.PmsBrandVo;
import com.danbro.search.controller.vo.PmsCategoryVo;
import com.danbro.search.rpc.fallbacks.PmsFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author liweimo
 */
@Component
@FeignClient(name = "service-pms", fallback = PmsFallback.class)
public interface PmsClient {
    /**
     * 通过属性ID获取属性信息
     *
     * @param attrId 属性ID
     * @return 属性信息
     */
    @GetMapping("product/attr/info/{attrId}")
    ResultBean<PmsAttrDetailVo> getAttrInfo(@PathVariable Long attrId);

    /**
     * 查找分类信息
     *
     * @param categoryId 分类ID
     * @return 分类信息
     */
    @GetMapping("product/category/info/{categoryId}")
    ResultBean<PmsCategoryVo> getCategoryInfo(@PathVariable Long categoryId);

    /**
     * 查找品牌信息
     *
     * @param brandId 品牌ID
     * @return 品牌信息
     */
    @GetMapping("product/brand/info/{brandId}")
    ResultBean<PmsBrandVo> getBrandInfo(@PathVariable Long brandId);

}
