package com.danbro.ware.controller;

import java.util.List;
import com.danbro.common.entity.ResultBean;
import com.danbro.common.entity.ResultPageBean;
import com.danbro.common.enums.PageParam;
import com.danbro.service.common.validtors.groups.Insert;
import com.danbro.service.common.validtors.groups.Update;
import com.danbro.ware.controller.vo.MergePurchaseVo;
import com.danbro.ware.controller.vo.WmsPurchaseDetailVo;
import com.danbro.ware.controller.vo.WmsPurchaseVo;
import com.danbro.ware.entity.WmsPurchase;
import com.danbro.ware.entity.WmsPurchaseDetail;
import com.danbro.ware.service.WmsPurchaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author makejava
 * @since 2021-01-28 19:06:16
 */
@Api(tags = "采购信息(WmsPurchase)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("ware/purchase")
@Setter
public class WmsPurchaseController {
    private WmsPurchaseService wmsPurchaseService;

    @ApiOperation("分页查询采购信息")
    @GetMapping("list")
    public ResultPageBean<WmsPurchaseVo, WmsPurchase> getPurchaseList(@RequestParam("page") Long page,
                                                                      @RequestParam("limit") Long limit,
                                                                      @RequestParam(value = "key", required = false) String key,
                                                                      @RequestParam(value = "status", required = false) Integer status) {
        PageParam<WmsPurchase> pageParam = new PageParam<WmsPurchase>().setLimit(limit).setPage(page);
        return ResultPageBean.ofSuccess(wmsPurchaseService.queryPageByCondition(pageParam, key, status));
    }

    @ApiOperation("添加采购信息")
    @PostMapping("")
    public ResultBean<WmsPurchaseVo> insertPurchase(@Validated(Insert.class) @RequestBody WmsPurchaseVo wmsPurchaseVo) {
        return ResultBean.ofSuccess(wmsPurchaseService.insertPurchase(wmsPurchaseVo));
    }

    @ApiOperation("修改采购信息")
    @PutMapping("")
    public ResultBean<WmsPurchaseVo> updatePurchase(@Validated(Update.class) @RequestBody WmsPurchaseVo wmsPurchaseVo) {
        return ResultBean.ofSuccess(wmsPurchaseService.updatePurchase(wmsPurchaseVo));
    }

    @ApiOperation("查询采购的详细信息")
    @GetMapping("info/{purchaseId}")
    public ResultBean<WmsPurchaseVo> getPurchaseInfo(@PathVariable Long purchaseId) {
        return ResultBean.ofSuccess(wmsPurchaseService.getPurchaseInfoById(purchaseId));
    }

    @ApiOperation("批量删除采购单")
    @DeleteMapping("")
    public ResultBean<?> batchDeletePurchase(@RequestBody List<Long> purchaseList) {
        wmsPurchaseService.batchDeletePurchase(purchaseList);
        return ResultBean.ofSuccess();
    }

    @ApiOperation("获取未领取的采购信息")
    @GetMapping("unreceive/list")
    public ResultPageBean<WmsPurchaseVo, WmsPurchase> getUnreceivePurchaseList(@RequestParam("page") Long page,
                                                                               @RequestParam("limit") Long limit) {
        PageParam<WmsPurchase> pageParam = new PageParam<WmsPurchase>().setLimit(limit).setPage(page);
        return ResultPageBean.ofSuccess(wmsPurchaseService.queryPageUnreceiveByCondition(pageParam));
    }

    @ApiOperation("合并采购单")
    @PostMapping("merge")
    public ResultBean<?> mergePurchase(@Validated @RequestBody MergePurchaseVo mergePurchaseVo) {
        wmsPurchaseService.mergePurchase(mergePurchaseVo);
        return ResultBean.ofSuccess();
    }

    @ApiOperation("领取采购单")
    @PostMapping("received")
    public ResultBean<?> receivePurchase(@RequestBody List<Long> purchaseIdList) {
        wmsPurchaseService.receivePurchase(purchaseIdList);
        return ResultBean.ofSuccess();
    }
}