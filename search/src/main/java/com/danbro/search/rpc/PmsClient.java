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

@Component
@FeignClient(name = "service-pms", fallback = PmsFallback.class)
public interface PmsClient {

    @GetMapping("product/attr/info/{attrId}")
    ResultBean<PmsAttrDetailVo> getAttrInfo(@PathVariable Long attrId);

    @GetMapping("product/category/info/{categoryId}")
    ResultBean<PmsCategoryVo> getCategoryInfo(@PathVariable Long categoryId);

    @GetMapping("product/brand/info/{brandId}")
    ResultBean<PmsBrandVo> getBrandInfo(@PathVariable Long brandId);

}
