package com.danbro.ware.service.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.danbro.common.enums.PageParam;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.common.utils.MyObjectUtils;
import com.danbro.common.utils.Pagination;
import com.danbro.common.utils.Query;
import com.danbro.ware.controller.vo.WmsWareSkuVo;
import com.danbro.ware.entity.WmsWareSku;
import com.danbro.ware.mapper.WmsWareSkuMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.ware.service.WmsWareSkuService;
import org.springframework.stereotype.Service;

/**
 * 商品库存(WmsWareSku)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:06:16
 */
@Service
public class WmsWareSkuServiceImpl extends ServiceImpl<WmsWareSkuMapper, WmsWareSku> implements WmsWareSkuService {

    @Override
    public Pagination<WmsWareSkuVo, WmsWareSku> queryPageByCondition(PageParam<WmsWareSku> pageParam, Long skuId, Long wareId) {
        LambdaQueryWrapper<WmsWareSku> queryWrapper = new QueryWrapper<WmsWareSku>().lambda();
        if (MyObjectUtils.isNotNull(skuId) && skuId > 0) {
            queryWrapper.like(WmsWareSku::getSkuId, skuId).or().eq(WmsWareSku::getSkuId, skuId);
        }
        if (MyObjectUtils.isNotNull(wareId) && wareId > 0) {
            queryWrapper.eq(WmsWareSku::getWareId, wareId);
        }
        return new Pagination<>(this.page(new Query<WmsWareSku>().getPage(pageParam), queryWrapper), WmsWareSkuVo.class);
    }

    @Override
    public WmsWareSkuVo insertWareSku(WmsWareSkuVo wmsWareSkuVo) {
        WmsWareSku wmsWareSku = wmsWareSkuVo.convertToEntity();
        boolean result = this.save(wmsWareSku);
        return MyCurdUtils.insertOrUpdate(wmsWareSkuVo.convertToVo(wmsWareSku), result, ResponseCode.INSERT_FAILURE);
    }

    @Override
    public WmsWareSkuVo updateWareSku(WmsWareSkuVo wmsWareSkuVo) {
        WmsWareSku wmsWareSku = wmsWareSkuVo.convertToEntity();
        boolean result = this.updateById(wmsWareSku);
        return MyCurdUtils.insertOrUpdate(wmsWareSkuVo.convertToVo(wmsWareSku), result, ResponseCode.INSERT_FAILURE);
    }

    @Override
    public WmsWareSkuVo getWareSkuInfoById(Long wareSkuId) {
        WmsWareSku wmsWareSku = this.getById(wareSkuId);
        return MyCurdUtils.select(WmsWareSkuVo.builder().build().convertToVo(wmsWareSku), ResponseCode.NOT_FOUND);
    }

    @Override
    public void batchDeleteWareSku(List<Long> wareSkuIdList) {
        MyCurdUtils.batchDelete(this.removeByIds(wareSkuIdList), ResponseCode.DELETE_FAILURE);
    }

    @Override
    public Boolean hasStockBySkuId(Long skuId) {
        List<WmsWareSku> wareSkuList = MyCurdUtils.selectList(this.list(new QueryWrapper<WmsWareSku>().lambda().eq(WmsWareSku::getSkuId, skuId)), ResponseCode.NOT_FOUND);
        // 计算出 sku 的总库存数（所有仓库的库存）
        AtomicReference<Integer> totalStock = new AtomicReference<>(0);
        wareSkuList.forEach(sku -> totalStock.updateAndGet(v -> v + sku.getStock()));
        return totalStock.get() > 0;
    }
}