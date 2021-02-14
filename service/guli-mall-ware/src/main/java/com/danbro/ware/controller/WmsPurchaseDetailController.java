package com.danbro.ware.controller;

import java.util.List;
import com.danbro.common.entity.ResultBean;
import com.danbro.common.entity.ResultPageBean;
import com.danbro.common.enums.PageParam;
import com.danbro.service.common.validtors.groups.Insert;
import com.danbro.service.common.validtors.groups.Update;
import com.danbro.ware.controller.vo.WmsPurchaseDetailVo;
import com.danbro.ware.controller.vo.WmsWareSkuVo;
import com.danbro.ware.entity.WmsPurchaseDetail;
import com.danbro.ware.service.WmsPurchaseDetailService;
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
@Api(tags = "(WmsPurchaseDetail)")
@Validated
@RestController
@AllArgsConstructor
@Setter
@RequestMapping("ware/purchasedetail")
public class WmsPurchaseDetailController {
    private WmsPurchaseDetailService wmsPurchaseDetailService;

    @ApiOperation("分页查询采购单")
    @GetMapping("list")
    public ResultPageBean<WmsPurchaseDetailVo, WmsPurchaseDetail> getPurchaseDetailList(@RequestParam("page") Long page,
                                                                                        @RequestParam("limit") Long limit,
                                                                                        @RequestParam(value = "key", required = false) String key,
                                                                                        @RequestParam(value = "status", required = false) Integer status,
                                                                                        @RequestParam(value = "wareId", required = false) Long wareId) {
        PageParam<WmsPurchaseDetail> pageParam = new PageParam<WmsPurchaseDetail>().setLimit(limit).setPage(page);
        return ResultPageBean.ofSuccess(wmsPurchaseDetailService.queryPageByCondition(pageParam, key, status, wareId));
    }
    @ApiOperation("查询采购单的详细信息")
    @GetMapping("info/{purchaseDetailId}")
    public ResultBean<WmsPurchaseDetailVo> getPurchaseDetailInfo(@PathVariable Long purchaseDetailId) {
        return ResultBean.ofSuccess(wmsPurchaseDetailService.getPurchaseDetailInfoById(purchaseDetailId));
    }

    @ApiOperation("添加采购单")
    @PostMapping("")
    public ResultBean<WmsPurchaseDetailVo> insertPurchaseDetail(@Validated(Insert.class) @RequestBody WmsPurchaseDetailVo wmsPurchaseDetailVo) {
        return ResultBean.ofSuccess(wmsPurchaseDetailService.insertPurchaseDetail(wmsPurchaseDetailVo));
    }

    @ApiOperation("修改采购单")
    @PutMapping("")
    public ResultBean<WmsPurchaseDetailVo> updatePurchaseDetail(@Validated(Update.class) @RequestBody WmsPurchaseDetailVo wmsPurchaseDetailVo) {
        return ResultBean.ofSuccess(wmsPurchaseDetailService.updatePurchaseDetail(wmsPurchaseDetailVo));
    }

    @ApiOperation("批量删除采购单")
    @DeleteMapping("")
    public ResultBean<?> batchDeleteWareSku(@RequestBody List<Long> purchaseDetailList) {
        wmsPurchaseDetailService.batchDeletePurchaseDetail(purchaseDetailList);
        return ResultBean.ofSuccess();
    }


}