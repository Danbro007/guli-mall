package com.danbro.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.PageParam;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.exceptions.GuliMallException;
import com.danbro.common.utils.*;
import com.danbro.ware.controller.vo.OmsOrderItem;
import com.danbro.ware.controller.vo.WmsLockStockResultVo;
import com.danbro.ware.controller.vo.WmsWareSkuVo;
import com.danbro.ware.entity.WmsWareSku;
import com.danbro.ware.mapper.WmsWareSkuMapper;
import com.danbro.ware.service.WmsWareSkuService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
        List<WmsWareSku> wareSkuList = this.list(new QueryWrapper<WmsWareSku>().lambda().eq(WmsWareSku::getSkuId, skuId));
        // 没查找库存数据就返回false
        if (MyCollectionUtils.isEmpty(wareSkuList)) {
            return false;
        }
        // 计算出 sku 的总库存数（所有仓库的库存）
        AtomicReference<Integer> totalStock = new AtomicReference<>(0);
        wareSkuList.forEach(sku -> totalStock.updateAndGet(v -> v + sku.getStock()));
        return totalStock.get() > 0;
    }

    @Override
    public List<WmsLockStockResultVo> lockStock(List<OmsOrderItem> items) {
        List<WmsLockStockResultVo> resultVos = new ArrayList<>();
        // 1、循环遍历所有的商品
        for (OmsOrderItem item : items) {
            // 锁库存成功标志符号，默认是 false
            boolean lockItemSuccess = false;
            // 2、查找出有当前商品库存的仓库ID（库存数-被锁定的库存数）
            List<Long> wareIdList = this.baseMapper.getWareIdListHasStock(item.getSkuId());
            // 4、遍历所有的仓库开始锁库存
            for (Long wareId : wareIdList) {
                Long result = this.baseMapper.lockStockBySkuId(item.getSkuId(), wareId, item.getSkuQuantity());
                // 锁库存成功跳出循环锁下一个商品的库存
                if (result == 1) {
                    WmsLockStockResultVo resultVo = new WmsLockStockResultVo().
                            setStock(item.getSkuQuantity()).
                            setSkuId(item.getSkuId()).
                            setSuccess(true);
                    resultVos.add(resultVo);
                    lockItemSuccess = true;
                    break;
                }
            }
            // 只要有一个锁库存失败则停止后面的锁库存操作
            if (!lockItemSuccess) {
                throw new GuliMallException(ResponseCode.LOCK_STOCK_FAILURE);
            }
        }
        return resultVos;
    }
}