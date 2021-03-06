package com.danbro.ware.controller;

import com.danbro.common.entity.ResultBean;
import com.danbro.common.entity.ResultPageBean;
import com.danbro.common.enums.PageParam;
import com.danbro.service.common.validtors.groups.Insert;
import com.danbro.service.common.validtors.groups.Update;
import com.danbro.ware.controller.vo.OrderToResponseVo;
import com.danbro.ware.controller.vo.WmsLockStockResultVo;
import com.danbro.ware.controller.vo.WmsWareOrderTaskDetailVo;
import com.danbro.ware.controller.vo.WmsWareSkuVo;
import com.danbro.ware.entity.WmsWareSku;
import com.danbro.ware.service.WmsWareSkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author makejava
 * @since 2021-01-28 19:06:16
 */
@Api(tags = "商品库存(WmsWareSku)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("ware/waresku")
@Setter
public class WmsWareSkuController {
    private WmsWareSkuService wmsWareSkuService;

    @ApiOperation("分页查询sku库存")
    @GetMapping("list")
    public ResultPageBean<WmsWareSkuVo, WmsWareSku> getWareSkuList(@RequestParam("page") Long page,
                                                                   @RequestParam("limit") Long limit,
                                                                   @RequestParam(value = "skuId", required = false) Long skuId,
                                                                   @RequestParam(value = "wareId", required = false) Long wareId) {
        PageParam<WmsWareSku> pageParam = new PageParam<WmsWareSku>().setLimit(limit).setPage(page);
        return ResultPageBean.ofSuccess(wmsWareSkuService.queryPageByCondition(pageParam, skuId, wareId));
    }

    @ApiOperation("查询sku库存的详细信息")
    @GetMapping("info/{wareSkuId}")
    public ResultBean<WmsWareSkuVo> getWareSkuInfo(@PathVariable Long wareSkuId) {
        return ResultBean.ofSuccess(wmsWareSkuService.getWareSkuInfoById(wareSkuId));
    }

    @ApiOperation("添加sku库存")
    @PostMapping("")
    public ResultBean<WmsWareSkuVo> insertWareSku(@Validated(Insert.class) @RequestBody WmsWareSkuVo wmsWareSkuVo) {
        return ResultBean.ofSuccess(wmsWareSkuService.insertWareSku(wmsWareSkuVo));
    }

    @ApiOperation("修改sku库存")
    @PutMapping("")
    public ResultBean<WmsWareSkuVo> updateWareSku(@Validated(Update.class) @RequestBody WmsWareSkuVo wmsWareSkuVo) {
        return ResultBean.ofSuccess(wmsWareSkuService.updateWareSku(wmsWareSkuVo));
    }

    @ApiOperation("批量删除商品库存")
    @DeleteMapping("")
    public ResultBean<?> batchDeleteWareSku(@RequestBody List<Long> wareSkuIdList) {
        wmsWareSkuService.batchDeleteWareSku(wareSkuIdList);
        return ResultBean.ofSuccess();
    }

    @ApiOperation("查询Sku还有没有库存")
    @GetMapping("hasStock/{skuId}")
    public ResultBean<Boolean> hasStock(@PathVariable Long skuId) {
        return ResultBean.ofSuccess(wmsWareSkuService.hasStockBySkuId(skuId));
    }

    @ApiOperation("批量锁商品的库存")
    @PostMapping("lockStock")
    public ResultBean<List<WmsLockStockResultVo>> lockStock(@RequestBody OrderToResponseVo responseVo) {
        return ResultBean.ofSuccess(wmsWareSkuService.lockStock(responseVo));
    }

    @ApiOperation("通过订单号查询到对应的还处于库存锁定的商品库存信息")
    @GetMapping("stock/list/{orderSn}")
    public ResultBean<List<WmsWareOrderTaskDetailVo>> getOrderTaskDetailList(@PathVariable String orderSn) {
        return ResultBean.ofSuccess(wmsWareSkuService.getOrderTaskDetailListByOrderSn(orderSn));
    }
}