package com.danbro.product.controller;

import com.danbro.common.entity.ResultBean;
import com.danbro.product.controller.vo.PmsSpuInfoVo;
import com.danbro.product.controller.vo.spu.Spu;
import com.danbro.product.service.PmsSpuInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author makejava
 * @since 2021-01-28 18:56:55
 */
@Api(tags = "spu信息(PmsSpuInfo)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("product/spuinfo")
@Setter
public class PmsSpuInfoController {
    private PmsSpuInfoService pmsSpuInfoService;


    @ApiOperation("添加Spu")
    @PostMapping
    public ResultBean<?> insertSpuInfo(@RequestBody PmsSpuInfoVo spu) {
        pmsSpuInfoService.insertSpuInfo(spu);
        return ResultBean.ofSuccess();
    }

}