package com.danbro.coupon.service.impl;

import java.util.Arrays;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.PageParam;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.ConvertUtils;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.common.utils.MyStrUtils;
import com.danbro.common.utils.Pagination;
import com.danbro.common.utils.Query;
import com.danbro.coupon.controller.vo.SmsSeckillPromotionVo;
import com.danbro.coupon.entity.SmsSeckillPromotion;
import com.danbro.coupon.mapper.SmsSeckillPromotionMapper;
import com.danbro.coupon.service.SmsSeckillPromotionService;
import org.springframework.stereotype.Service;

/**
 * 秒杀活动(SmsSeckillPromotion)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Service
public class SmsSeckillPromotionServiceImpl extends ServiceImpl<SmsSeckillPromotionMapper, SmsSeckillPromotion> implements SmsSeckillPromotionService {

    @Override
    public Pagination<SmsSeckillPromotionVo, SmsSeckillPromotion> queryPage(PageParam<SmsSeckillPromotion> pageParam, String key) {
        LambdaQueryWrapper<SmsSeckillPromotion> queryWrapper = new QueryWrapper<SmsSeckillPromotion>().lambda();
        if (MyStrUtils.isNotEmpty(key)) {
            queryWrapper.like(SmsSeckillPromotion::getId, key).or().like(SmsSeckillPromotion::getTitle, key);
        }
        IPage<SmsSeckillPromotion> page = this.page(new Query<SmsSeckillPromotion>().getPage(pageParam), queryWrapper);
        return new Pagination<>(page, SmsSeckillPromotionVo.class);
    }

    @Override
    public SmsSeckillPromotionVo insert(SmsSeckillPromotionVo smsSeckillPromotionVo) {
        SmsSeckillPromotion smsSeckillPromotion = smsSeckillPromotionVo.convertToEntity();
        boolean save = this.save(smsSeckillPromotion);
        MyCurdUtils.insertOrUpdate(save, ResponseCode.INSERT_FAILURE);
        return ConvertUtils.convert(smsSeckillPromotion, SmsSeckillPromotionVo.class);
    }

    @Override
    public SmsSeckillPromotionVo update(SmsSeckillPromotionVo smsSeckillPromotionVo) {
        SmsSeckillPromotion smsSeckillPromotion = smsSeckillPromotionVo.convertToEntity();
        boolean save = this.updateById(smsSeckillPromotion);
        MyCurdUtils.insertOrUpdate(save, ResponseCode.INSERT_FAILURE);
        return ConvertUtils.convert(smsSeckillPromotion, SmsSeckillPromotionVo.class);
    }

    @Override
    public void batchDeletePromotion(Long[] ids) {
        MyCurdUtils.delete(this.removeByIds(Arrays.asList(ids)), ResponseCode.DELETE_FAILURE);
    }
}