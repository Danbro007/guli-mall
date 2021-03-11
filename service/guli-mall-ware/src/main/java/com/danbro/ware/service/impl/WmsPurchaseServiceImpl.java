package com.danbro.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.PageParam;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.enums.wms.PurchaseDetailStatus;
import com.danbro.common.utils.*;
import com.danbro.ware.controller.vo.*;
import com.danbro.ware.entity.WmsPurchase;
import com.danbro.ware.entity.WmsPurchaseDetail;
import com.danbro.ware.entity.WmsWareSku;
import com.danbro.ware.mapper.WmsPurchaseMapper;
import com.danbro.ware.service.PmsFeignService;
import com.danbro.ware.service.WmsPurchaseDetailService;
import com.danbro.ware.service.WmsPurchaseService;
import com.danbro.ware.service.WmsWareSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 采购信息(WmsPurchase)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:06:16
 */
@Service
public class WmsPurchaseServiceImpl extends ServiceImpl<WmsPurchaseMapper, WmsPurchase> implements WmsPurchaseService {

    @Autowired
    WmsPurchaseDetailService wmsPurchaseDetailService;

    @Autowired
    PmsFeignService pmsFeignService;

    @Autowired
    WmsWareSkuService wmsWareSkuService;

    @Override
    public Pagination<WmsPurchaseVo, WmsPurchase> queryPageByCondition(PageParam<WmsPurchase> pageParam, String key, Integer status) {
        LambdaQueryWrapper<WmsPurchase> queryWrapper = new QueryWrapper<WmsPurchase>().lambda();
        if (MyStrUtils.isNotEmpty(key)) {
            queryWrapper.like(WmsPurchase::getId, key).or().eq(WmsPurchase::getId, key).or().like(WmsPurchase::getAssigneeName, key);
        }
        if (MyObjectUtils.isNotNull(status)) {
            queryWrapper.eq(WmsPurchase::getStatus, status);
        }
        return new Pagination<>(this.page(new Query<WmsPurchase>().getPage(pageParam), queryWrapper), WmsPurchaseVo.class);
    }

    @Override
    public void batchDeletePurchase(List<Long> purchaseList) {
        MyCurdUtils.batchDelete(this.removeByIds(purchaseList), ResponseCode.DELETE_FAILURE);
    }

    @Override
    public WmsPurchaseVo insertPurchase(WmsPurchaseVo wmsPurchaseVo) {
        wmsPurchaseVo.setStatus(PurchaseDetailStatus.NEW);
        WmsPurchase wmsPurchase = wmsPurchaseVo.convertToEntity();
        boolean result = this.save(wmsPurchase);
        return MyCurdUtils.insertOrUpdate(wmsPurchaseVo.convertToVo(wmsPurchase), result, ResponseCode.INSERT_FAILURE);
    }

    @Override
    public WmsPurchaseVo updatePurchase(WmsPurchaseVo wmsPurchaseVo) {
        WmsPurchase wmsPurchase = wmsPurchaseVo.convertToEntity();
        boolean result = this.updateById(wmsPurchase);
        return MyCurdUtils.insertOrUpdate(wmsPurchaseVo.convertToVo(wmsPurchase), result, ResponseCode.UPDATE_FAILURE);
    }

    @Override
    public WmsPurchaseVo getPurchaseInfoById(Long purchaseId) {
        WmsPurchase purchase = this.getById(purchaseId);
        return MyCurdUtils.select(WmsPurchaseVo.builder().build().convertToVo(purchase), ResponseCode.NOT_FOUND);
    }

    @Override
    public Pagination<WmsPurchaseVo, WmsPurchase> queryPageUnreceiveByCondition(PageParam<WmsPurchase> pageParam) {
        LambdaQueryWrapper<WmsPurchase> queryWrapper = new QueryWrapper<WmsPurchase>().lambda();
        // 查找出【已分配】或刚【新建】的采购信息
        queryWrapper.eq(WmsPurchase::getStatus, PurchaseDetailStatus.ALLOCATED).or().eq(WmsPurchase::getStatus, PurchaseDetailStatus.NEW);
        return new Pagination<>(this.page(new Query<WmsPurchase>().getPage(pageParam), queryWrapper), WmsPurchaseVo.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void mergePurchase(MergePurchaseVo mergePurchaseVo) {
        // 没有采购信息则直接创建一个
        if (MyObjectUtils.isNull(mergePurchaseVo.getPurchaseId())) {
            WmsPurchase wmsPurchase = new WmsPurchase().setStatus(PurchaseDetailStatus.NEW);
            MyCurdUtils.insertOrUpdate(wmsPurchase, this.save(wmsPurchase), ResponseCode.INSERT_FAILURE);
            mergePurchaseVo.setPurchaseId(wmsPurchase.getId());
        }
        // 先更新采购单的数据
        List<WmsPurchaseDetail> purchaseDetails = wmsPurchaseDetailService.list(new QueryWrapper<WmsPurchaseDetail>().lambda().in(WmsPurchaseDetail::getId, mergePurchaseVo.getItems()));
        Long wareId = purchaseDetails.get(0).getWareId();
        purchaseDetails.forEach(detail -> detail.setPurchaseId(mergePurchaseVo.getPurchaseId()).setStatus(PurchaseDetailStatus.ALLOCATED));
        MyCurdUtils.batchInsertOrUpdate(purchaseDetails, wmsPurchaseDetailService.updateBatchById(purchaseDetails), ResponseCode.UPDATE_FAILURE);
        // 更新采购信息状态为【新建】和【已分配】状态的
        LambdaUpdateWrapper<WmsPurchase> purchaseUpdateWrapper = new UpdateWrapper<WmsPurchase>().lambda().
                eq(WmsPurchase::getId, mergePurchaseVo.getPurchaseId()).
                eq(WmsPurchase::getStatus, PurchaseDetailStatus.NEW).or().
                eq(WmsPurchase::getStatus, PurchaseDetailStatus.ALLOCATED).
                set(WmsPurchase::getStatus, PurchaseDetailStatus.ALLOCATED).
                set(WmsPurchase::getWareId, wareId);
        MyCurdUtils.batchInsertOrUpdate(purchaseDetails, this.update(purchaseUpdateWrapper), ResponseCode.UPDATE_FAILURE);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void receivePurchase(List<Long> purchaseIdList) {
        List<WmsPurchase> purchaseList = MyCurdUtils.selectList(this.list(new QueryWrapper<WmsPurchase>().
                        lambda().
                        in(WmsPurchase::getId, purchaseIdList)),
                ResponseCode.NOT_FOUND);
        // 只更新采购单状态为【新建】或者【已分配】的变成【已领取】
        purchaseList.stream().
                filter(purchase -> purchase.getStatus() == PurchaseDetailStatus.NEW || purchase.getStatus() == PurchaseDetailStatus.ALLOCATED)
                .collect(Collectors.toList())
                .forEach(purchase -> {
                    // 更新采购项的状态为【已领取】
                    purchase.setStatus(PurchaseDetailStatus.PURCHASING);
                    wmsPurchaseDetailService.update(new UpdateWrapper<WmsPurchaseDetail>().
                            lambda().
                            eq(WmsPurchaseDetail::getPurchaseId, purchase.getId()).
                            set(WmsPurchaseDetail::getStatus, PurchaseDetailStatus.PURCHASING));
                });
        MyCurdUtils.batchInsertOrUpdate(purchaseList, this.updateBatchById(purchaseList), ResponseCode.UPDATE_FAILURE);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void finishPurchase(DonePurchaseVo donePurchaseVo) {
        Long id = donePurchaseVo.getId();
        List<DonePurchaseDetailVo> purchaseDetailVos = donePurchaseVo.getItems();
        // 先更新采购项的状态，如果都采购成功则把采购单的状态设置为【采购成功】
        boolean flag = false;
        for (DonePurchaseDetailVo detail : purchaseDetailVos) {
            LambdaUpdateWrapper<WmsPurchaseDetail> lambda = new UpdateWrapper<WmsPurchaseDetail>().lambda();
            // 采购成功的都要入库
            if (detail.getStatus() == PurchaseDetailStatus.DONE) {
                lambda = new UpdateWrapper<WmsPurchaseDetail>().
                        lambda().eq(WmsPurchaseDetail::getId, detail.getItemId()).set(WmsPurchaseDetail::getStatus, PurchaseDetailStatus.DONE);
                // 产品入库
                productInBound(detail);
            } else if (detail.getStatus() == PurchaseDetailStatus.FAILURE) {
                // 采购失败的只更新采购状态
                lambda = new UpdateWrapper<WmsPurchaseDetail>().lambda().
                        eq(WmsPurchaseDetail::getId, detail.getItemId()).set(WmsPurchaseDetail::getStatus, PurchaseDetailStatus.FAILURE);
                flag = true;
            }
            // 更细采购状态
            MyCurdUtils.insertOrUpdate(wmsPurchaseDetailService.update(lambda), ResponseCode.UPDATE_FAILURE);
        }
        // 更新采购单信息
        // 没有全部采购成功则把整个采购单的状态设置为【采购失败】状态  false:都采购成功
        LambdaUpdateWrapper<WmsPurchase> updateWrapper = new UpdateWrapper<WmsPurchase>().
                lambda().
                eq(WmsPurchase::getId, id).
                set(WmsPurchase::getStatus, flag ? PurchaseDetailStatus.FAILURE : PurchaseDetailStatus.DONE);
        MyCurdUtils.insertOrUpdate(this.update(updateWrapper), ResponseCode.UPDATE_FAILURE);
    }

    /**
     * 商品入库
     *
     * @param detail 采购成功的项目
     */
    private void productInBound(DonePurchaseDetailVo detail) {
        WmsPurchaseDetail purchaseDetail = MyCurdUtils.select(wmsPurchaseDetailService.getById(detail.getItemId()), ResponseCode.NOT_FOUND);
        // 先查询到采购的商品信息（到 pms 查询）
        PmsSkuInfoVo pmsSkuInfoVo = MyCurdUtils.rpcResultHandle(pmsFeignService.getSkuInfo(purchaseDetail.getSkuId()));
        // 查询出入库之前的库存
        WmsWareSku wmsWareSku = MyCurdUtils.select(wmsWareSkuService.getOne(new QueryWrapper<WmsWareSku>().
                        lambda().
                        eq(WmsWareSku::getSkuId, pmsSkuInfoVo.getSkuId()).
                        eq(WmsWareSku::getWareId, purchaseDetail.getWareId())),
                ResponseCode.NOT_FOUND);
        // 更新产品的库存
        wmsWareSku.setStock(wmsWareSku.getStock() + purchaseDetail.getSkuNum());
        MyCurdUtils.insertOrUpdate(wmsWareSkuService.updateById(wmsWareSku), ResponseCode.UPDATE_FAILURE);
    }
}