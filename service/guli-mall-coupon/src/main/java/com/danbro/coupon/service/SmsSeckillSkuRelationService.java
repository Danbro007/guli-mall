package com.danbro.coupon.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.common.enums.PageParam;
import com.danbro.common.utils.Pagination;
import com.danbro.coupon.controller.vo.SmsSeckillSkuRelationVo;
import com.danbro.coupon.entity.SmsSeckillSkuRelation;


/**
 * 秒杀活动商品关联(SmsSeckillSkuRelation)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
public interface SmsSeckillSkuRelationService extends IService<SmsSeckillSkuRelation> {
    Pagination<SmsSeckillSkuRelationVo, SmsSeckillSkuRelation> queryPage(PageParam<SmsSeckillSkuRelation> pageParam, String key);

    SmsSeckillSkuRelationVo insert(SmsSeckillSkuRelationVo skuRelationVo);

    SmsSeckillSkuRelationVo update(SmsSeckillSkuRelationVo skuRelationVo);

    void batchDeleteSkuRelation(Long[] ids);

    SmsSeckillSkuRelationVo getInfoById(Long id);
}