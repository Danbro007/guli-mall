package com.danbro.search.rpc.fallbacks;

import com.danbro.common.entity.ResultBean;
import com.danbro.common.enums.ResponseCode;
import com.danbro.search.controller.vo.PmsAttrDetailVo;
import com.danbro.search.controller.vo.PmsBrandVo;
import com.danbro.search.controller.vo.PmsCategoryVo;
import com.danbro.search.rpc.PmsClient;
import org.springframework.stereotype.Component;

/**
 * @author liweimo
 * @Classname PmsAttrFallback
 * @Description TODO PmsClient的fallback
 * @Date 2021/2/23 20:48
 * @Created by Administrator
 */
@Component
public class PmsFallback implements PmsClient {
    @Override
    public ResultBean<PmsAttrDetailVo> getAttrInfo(Long attrId) {
        return ResultBean.ofFailure(ResponseCode.RPC_TIME_OUT);
    }

    @Override
    public ResultBean<PmsCategoryVo> getCategoryInfo(Long categoryId) {
        return ResultBean.ofFailure(ResponseCode.RPC_TIME_OUT);
    }

    @Override
    public ResultBean<PmsBrandVo> getBrandInfo(Long brandId) {
        return ResultBean.ofFailure(ResponseCode.RPC_TIME_OUT);
    }
}
