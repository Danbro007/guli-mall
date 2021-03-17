package com.danbro.seckill.service;

import com.danbro.seckill.vo.SmsSeckillSkuRelationVo;

import java.util.List;

/**
 * @author Danrbo
 * @Classname SecKillService
 * @Description TODO
 * @Date 2021/3/16 12:24
 */

public interface SecKillService {

    /**
     * 上架最近三天的秒杀的商品
     */
    void uploadLast3DaysSku();

    List<SmsSeckillSkuRelationVo> getCurrentSecKillSkuList();


    SmsSeckillSkuRelationVo getSecKillRelationInfo(Long skuId);

}
