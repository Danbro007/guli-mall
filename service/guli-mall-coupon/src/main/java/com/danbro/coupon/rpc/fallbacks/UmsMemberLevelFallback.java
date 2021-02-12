package com.danbro.coupon.rpc.fallbacks;

import com.danbro.common.entity.ResultBean;
import com.danbro.common.enums.ResponseCode;
import com.danbro.coupon.controller.vo.UmsMemberLevelVo;
import com.danbro.coupon.rpc.clients.UmsMemberLevelClient;
import org.springframework.stereotype.Component;

/**
 * @Classname UmsMemberLevelFallback
 * @Description TODO
 * @Date 2021/2/12 16:08
 * @Created by Administrator
 */
@Component
public class UmsMemberLevelFallback implements UmsMemberLevelClient {

    @Override
    public ResultBean<UmsMemberLevelVo> getMemberLevelInfoByName(String memberLevelName) {
        return ResultBean.ofFailure(ResponseCode.RPC_TIME_OUT);

    }
}
