package com.danbro.coupon.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.common.enums.PageParam;
import com.danbro.common.utils.Pagination;
import com.danbro.coupon.controller.vo.SmsSeckillSkuRelationVo;
import com.danbro.coupon.entity.SmsSeckillSkuRelation;

import java.util.List;


/**
 * 秒杀活动商品关联(SmsSeckillSkuRelation)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
public interface SmsSeckillSkuRelationService extends IService<SmsSeckillSkuRelation> {
    /**
     * 分页查询秒杀活动与秒杀商品的关系
     *
     * @param pageParam 分页参数
     * @param key       关键字
     * @return 关系列表
     */
    Pagination<SmsSeckillSkuRelationVo, SmsSeckillSkuRelation> queryPage(PageParam<SmsSeckillSkuRelation> pageParam, String key, Long promotionSessionId);

    /**
     * 添加秒杀活动与秒杀商品的关系
     *
     * @param skuRelationVo 添加关系数据
     * @return 添加完毕后的关系
     */
    SmsSeckillSkuRelationVo insert(SmsSeckillSkuRelationVo skuRelationVo);

    SmsSeckillSkuRelationVo update(SmsSeckillSkuRelationVo skuRelationVo);

    void batchDeleteSkuRelation(Long[] ids);

    SmsSeckillSkuRelationVo getInfoById(Long id);

    /**
     * 通过秒杀场次的ID查询到秒杀的商品关系
     *
     * @param promotionSessionId 秒杀场次ID
     * @return 关系数据
     */
    List<SmsSeckillSkuRelationVo> getRelationByPromotionSessionId(Long promotionSessionId);
}