package com.danbro.coupon.controller;

import java.util.List;
import com.danbro.common.entity.ResultBean;
import com.danbro.coupon.controller.vo.SmsMemberPriceVo;
import com.danbro.coupon.service.SmsMemberPriceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Api(tags = "商品会员价格(SmsMemberPrice)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("member/memberprice")
@Setter
public class SmsMemberPriceController {
    private SmsMemberPriceService smsMemberPriceService;

    @ApiOperation("添加单个会员价格")
    @PostMapping("")
    public ResultBean<SmsMemberPriceVo> insertMemberPrice(@Validated @RequestBody SmsMemberPriceVo smsMemberPriceVo){
        return ResultBean.ofSuccess(smsMemberPriceService.insertMemberPrice(smsMemberPriceVo));
    }

    @ApiOperation("批量添加会员价格")
    @PostMapping("list")
    public ResultBean<List<SmsMemberPriceVo>> insertMemberPrice(@Validated @RequestBody List<SmsMemberPriceVo> smsMemberPriceVoList){
        return ResultBean.ofSuccess(smsMemberPriceService.batchInsertMemberPrice(smsMemberPriceVoList));
    }


}