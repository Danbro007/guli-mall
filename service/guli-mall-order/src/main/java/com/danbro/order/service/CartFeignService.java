package com.danbro.order.service;

import com.danbro.common.entity.ResultBean;
import com.danbro.order.controller.vo.CartItemVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author Danrbo
 * @Classname CartFeignService
 * @Description TODO
 * @Date 2021/3/9 12:44
 */
@Component
@FeignClient(value = "service-cart")
public interface CartFeignService {

    @ApiOperation("获取会员购物车里所有选中的商品信息")
    @GetMapping("cartList/{memberId}")
    ResultBean<List<CartItemVo>> getCartItemList(@PathVariable Long memberId);
}
