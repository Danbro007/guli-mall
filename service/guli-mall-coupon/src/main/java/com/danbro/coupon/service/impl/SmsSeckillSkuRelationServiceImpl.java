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
import com.danbro.coupon.controller.vo.SmsSeckillSkuRelationVo;
import com.danbro.coupon.entity.SmsSeckillSkuRelation;
import com.danbro.coupon.mapper.SmsSeckillSkuRelationMapper;
import com.danbro.coupon.service.SmsSeckillSkuRelationService;
import org.springframework.stereotype.Service;

/**
 * 秒杀活动商品关联(SmsSeckillSkuRelation)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Service
public class SmsSeckillSkuRelationServiceImpl extends ServiceImpl<SmsSeckillSkuRelationMapper, SmsSeckillSkuRelation> implements SmsSeckillSkuRelationService {

    @Override
    public Pagination<SmsSeckillSkuRelationVo, SmsSeckillSkuRelation> queryPage(PageParam<SmsSeckillSkuRelation> pageParam, String key) {
        LambdaQueryWrapper<SmsSeckillSkuRelation> queryWrapper = new QueryWrapper<SmsSeckillSkuRelation>().lambda();
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
        boolean save = this.save(skuRelation);
        MyCurdUtils.insertOrUpdate(save, ResponseCode.INSERT_FAILURE);
        return ConvertUtils.convert(skuRelation, SmsSeckillSkuRelationVo.class);
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
}