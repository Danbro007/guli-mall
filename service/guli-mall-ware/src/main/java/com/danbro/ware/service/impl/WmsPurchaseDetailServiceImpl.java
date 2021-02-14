package com.danbro.ware.service.impl;

import java.util.List;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.danbro.common.enums.PageParam;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.common.utils.MyObjectUtils;
import com.danbro.common.utils.MyStrUtils;
import com.danbro.common.utils.Pagination;
import com.danbro.common.utils.Query;
import com.danbro.ware.controller.vo.WmsPurchaseDetailVo;
import com.danbro.ware.controller.vo.WmsWareSkuVo;
import com.danbro.ware.entity.WmsPurchaseDetail;
import com.danbro.ware.entity.WmsWareSku;
import com.danbro.ware.mapper.WmsPurchaseDetailMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.ware.service.WmsPurchaseDetailService;
import org.springframework.stereotype.Service;

/**
 * (WmsPurchaseDetail)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:06:16
 */
@Service
public class WmsPurchaseDetailServiceImpl extends ServiceImpl<WmsPurchaseDetailMapper, WmsPurchaseDetail> implements WmsPurchaseDetailService {

    @Override
    public Pagination<WmsPurchaseDetailVo, WmsPurchaseDetail> queryPageByCondition(PageParam<WmsPurchaseDetail> pageParam, String key, Integer status, Long wareId) {
        LambdaQueryWrapper<WmsPurchaseDetail> queryWrapper = new QueryWrapper<WmsPurchaseDetail>().lambda();
        if (MyStrUtils.isNotEmpty(key)) {
            queryWrapper.like(WmsPurchaseDetail::getId, key).or().eq(WmsPurchaseDetail::getId, key);
        }
        if (MyObjectUtils.isNotNull(status)) {
            queryWrapper.eq(WmsPurchaseDetail::getStatus, status);
        }
        if (MyObjectUtils.isNotNull(wareId)) {
            queryWrapper.eq(WmsPurchaseDetail::getWareId, wareId);
        }
        return new Pagination<>(this.page(new Query<WmsPurchaseDetail>().getPage(pageParam), queryWrapper), WmsPurchaseDetailVo.class);
    }

    @Override
    public WmsPurchaseDetailVo getPurchaseDetailInfoById(Long purchaseDetailId) {
        WmsPurchaseDetail purchaseDetail = this.getById(purchaseDetailId);
        return MyCurdUtils.select(WmsPurchaseDetailVo.builder().build().convertToVo(purchaseDetail), ResponseCode.NOT_FOUND);
    }

    @Override
    public WmsPurchaseDetailVo insertPurchaseDetail(WmsPurchaseDetailVo wmsPurchaseDetailVo) {
        WmsPurchaseDetail wmsPurchaseDetail = wmsPurchaseDetailVo.convertToEntity();
        boolean result = this.save(wmsPurchaseDetail);
        return MyCurdUtils.insertOrUpdate(wmsPurchaseDetailVo.convertToVo(wmsPurchaseDetail), result, ResponseCode.INSERT_FAILURE);
    }

    @Override
    public WmsPurchaseDetailVo updatePurchaseDetail(WmsPurchaseDetailVo wmsPurchaseDetailVo) {
        WmsPurchaseDetail wmsPurchaseDetail = wmsPurchaseDetailVo.convertToEntity();
        boolean result = this.updateById(wmsPurchaseDetail);
        return MyCurdUtils.insertOrUpdate(wmsPurchaseDetailVo.convertToVo(wmsPurchaseDetail), result, ResponseCode.UPDATE_FAILURE);
    }

    @Override
    public void batchDeletePurchaseDetail(List<Long> purchaseDetailList) {
        MyCurdUtils.batchDelete(this.removeByIds(purchaseDetailList), ResponseCode.DELETE_FAILURE);
    }
}