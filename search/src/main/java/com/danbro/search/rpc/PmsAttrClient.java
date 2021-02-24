package com.danbro.search.rpc;


import com.danbro.common.entity.ResultBean;
import com.danbro.search.controller.vo.PmsAttrDetailVo;
import com.danbro.search.rpc.fallbacks.PmsAttrFallback;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@FeignClient(name = "service-pms", fallback = PmsAttrFallback.class)
public interface PmsAttrClient {

    @GetMapping("product/attr/info/{attrId}")
    ResultBean<PmsAttrDetailVo> getAttrInfo(@PathVariable Long attrId);
}
