package com.danbro.ware.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.PageParam;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.common.utils.MyStrUtils;
import com.danbro.common.utils.Pagination;
import com.danbro.common.utils.Query;
import com.danbro.ware.controller.vo.FareVo;
import com.danbro.ware.controller.vo.UmsMemberReceiveAddressVo;
import com.danbro.ware.controller.vo.WmsWareInfoVo;
import com.danbro.ware.entity.WmsWareInfo;
import com.danbro.ware.mapper.WmsWareInfoMapper;
import com.danbro.ware.service.MemberFeignService;
import com.danbro.ware.service.WmsWareInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 仓库信息(WmsWareInfo)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:06:16
 */
@Service
public class WmsWareInfoServiceImpl extends ServiceImpl<WmsWareInfoMapper, WmsWareInfo> implements WmsWareInfoService {

    @Autowired
    MemberFeignService memberFeignService;

    @Override
    public Pagination<WmsWareInfoVo, WmsWareInfo> queryPageByCondition(PageParam<WmsWareInfo> pageParam, String key) {
        LambdaQueryWrapper<WmsWareInfo> queryWrapper = new QueryWrapper<WmsWareInfo>().lambda();
        if (MyStrUtils.isNotEmpty(key)) {
            queryWrapper.like(WmsWareInfo::getName, key).or().
                    like(WmsWareInfo::getAddress, key).or().
                    like(WmsWareInfo::getAreacode, key).or().
                    like(WmsWareInfo::getId, key).or().
                    eq(WmsWareInfo::getId, key);
        }
        return new Pagination<>(this.page(new Query<WmsWareInfo>().getPage(pageParam), queryWrapper), WmsWareInfoVo.class);
    }

    @Override
    public WmsWareInfoVo insertWare(WmsWareInfoVo wmsWareInfoVo) {
        WmsWareInfo wmsWareInfo = wmsWareInfoVo.convertToEntity();
        boolean result = this.save(wmsWareInfo);
        return MyCurdUtils.insertOrUpdate(wmsWareInfoVo.convertToVo(wmsWareInfo), result, ResponseCode.INSERT_FAILURE);
    }

    @Override
    public WmsWareInfoVo updateWare(WmsWareInfoVo wmsWareInfoVo) {
        WmsWareInfo wmsWareInfo = wmsWareInfoVo.convertToEntity();
        boolean result = this.updateById(wmsWareInfo);
        return MyCurdUtils.insertOrUpdate(wmsWareInfoVo.convertToVo(wmsWareInfo), result, ResponseCode.UPDATE_FAILURE);
    }

    @Override
    public WmsWareInfoVo getWareInfoById(Long wareId) {
        WmsWareInfo wmsWareInfo = this.getById(wareId);
        return MyCurdUtils.select(WmsWareInfoVo.builder().build().convertToVo(wmsWareInfo), ResponseCode.NOT_FOUND);
    }

    @Override
    public void batchDeleteWare(List<Long> wareIdList) {
        MyCurdUtils.batchDelete(this.removeByIds(wareIdList), ResponseCode.DELETE_FAILURE);
    }

    @Override
    public FareVo calculateFare(Long addressId) {
        FareVo fareVo = new FareVo();
        // 这里运费是用随机生成的
        fareVo.setFare(new BigDecimal(RandomUtil.randomInt(5, 20)));
        UmsMemberReceiveAddressVo addressVo = MyCurdUtils.rpcResultHandle(memberFeignService.getAddressInfoByAddressId(addressId));
        fareVo.setAddress(addressVo);
        return fareVo;
    }
}