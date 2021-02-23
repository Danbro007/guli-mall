package com.danbro.search.rpc.fallbacks;

import com.danbro.common.entity.ResultBean;
import com.danbro.common.enums.ResponseCode;
import com.danbro.search.controller.vo.PmsAttrDetailVo;
import com.danbro.search.rpc.PmsAttrClient;
import org.springframework.stereotype.Component;

/**
 * @Classname PmsAttrFallback
 * @Description TODO
 * @Date 2021/2/23 20:48
 * @Created by Administrator
 */
@Component
public class PmsAttrFallback implements PmsAttrClient {
    @Override
    public ResultBean<PmsAttrDetailVo> getAttrInfo(Long attrId) {
        return ResultBean.ofFailure(ResponseCode.RPC_TIME_OUT);
    }
}
