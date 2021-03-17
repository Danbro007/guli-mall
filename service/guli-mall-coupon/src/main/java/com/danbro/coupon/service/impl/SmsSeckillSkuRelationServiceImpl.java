package com.danbro.coupon.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.PageParam;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.*;
import com.danbro.coupon.controller.vo.SmsSeckillSkuRelationVo;
import com.danbro.coupon.entity.SmsSeckillSkuRelation;
import com.danbro.coupon.mapper.SmsSeckillSkuRelationMapper;
import com.danbro.coupon.service.SmsSeckillSkuRelationService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 秒杀活动商品关联(SmsSeckillSkuRelation)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Service
public class SmsSeckillSkuRelationServiceImpl extends ServiceImpl<SmsSeckillSkuRelationMapper, SmsSeckillSkuRelation> implements SmsSeckillSkuRelationService {

    @Override
    public Pagination<SmsSeckillSkuRelationVo, SmsSeckillSkuRelation> queryPage(PageParam<SmsSeckillSkuRelation> pageParam,
                                                                                String key,
                                                                                Long promotionSessionId) {
        LambdaQueryWrapper<SmsSeckillSkuRelation> queryWrapper = new QueryWrapper<SmsSeckillSkuRelation>().lambda();
        queryWrapper.eq(SmsSeckillSkuRelation::getPromotionSessionId, promotionSessionId);
        if (MyStrUtils.isNotEmpty(key)) {
            queryWrapper.like(SmsSeckillSkuRelation::getId, key).or().
                    like(SmsSeckillSkuRelation::getPromotionId, key).or().
                    like(SmsSeckillSkuRelation::getSkuId, key).or().
                    like(SmsSeckillSkuRelation::getPromotionSessionId, key);
        }
        IPage<SmsSeckillSkuRelation> page = this.page(new Query<SmsSeckillSkuRelation>().getPage(pageParam), queryWrapper);
        return new Pagination<>(page, SmsSeckillSkuRelationVo.class);
    }

    @Override
    public SmsSeckillSkuRelationVo insert(SmsSeckillSkuRelationVo skuRelationVo) {
        SmsSeckillSkuRelation skuRelation = skuRelationVo.convertToEntity();
        SmsSeckillSkuRelation relation = this.getOne(new QueryWrapper<SmsSeckillSkuRelation>().lambda().eq(SmsSeckillSkuRelation::getPromotionSessionId, skuRelationVo.getPromotionSessionId()).eq(SmsSeckillSkuRelation::getSkuId, skuRelationVo.getSkuId()));
        // 添加的秒杀商品在当前秒杀场次里有,则直接更新秒杀商品的数据
        if (MyObjectUtils.isNotNull(relation)) {
            relation.setSeckillCount(skuRelation.getSeckillCount().add(relation.getSeckillCount())).
                    setSeckillLimit(skuRelation.getSeckillLimit()).
                    setSeckillPrice(skuRelation.getSeckillPrice());
            boolean update = this.updateById(relation);
            MyCurdUtils.insertOrUpdate(update, ResponseCode.UPDATE_FAILURE);
            return ConvertUtils.convert(relation, SmsSeckillSkuRelationVo.class);
        } else {
            boolean save = this.save(skuRelation);
            MyCurdUtils.insertOrUpdate(save, ResponseCode.INSERT_FAILURE);
            return ConvertUtils.convert(skuRelation, SmsSeckillSkuRelationVo.class);
        }
    }

    @Override
    public SmsSeckillSkuRelationVo update(SmsSeckillSkuRelationVo skuRelationVo) {
        SmsSeckillSkuRelation skuRelation = skuRelationVo.convertToEntity();
        boolean update = this.updateById(skuRelation);
        MyCurdUtils.insertOrUpdate(update, ResponseCode.INSERT_FAILURE);
        return ConvertUtils.convert(skuRelation, SmsSeckillSkuRelationVo.class);
    }

    @Override
    public void batchDeleteSkuRelation(Long[] ids) {
        MyCurdUtils.delete(this.removeByIds(Arrays.asList(ids)), ResponseCode.DELETE_FAILURE);
    }

    @Override
    public SmsSeckillSkuRelationVo getInfoById(Long id) {
        SmsSeckillSkuRelation skuRelation = MyCurdUtils.select(this.getById(id), ResponseCode.NOT_FOUND);
        return ConvertUtils.convert(skuRelation, SmsSeckillSkuRelationVo.class);
    }

    @Override
    public List<SmsSeckillSkuRelationVo> getRelationByPromotionSessionId(Long promotionSessionId) {
        List<SmsSeckillSkuRelation> skuRelationList = this.list(new QueryWrapper<SmsSeckillSkuRelation>().lambda().eq(SmsSeckillSkuRelation::getPromotionSessionId, promotionSessionId));
        return ConvertUtils.batchConvert(skuRelationList, SmsSeckillSkuRelationVo.class);
    }
}