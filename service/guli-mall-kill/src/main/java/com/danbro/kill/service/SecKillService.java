package com.danbro.kill.service;

import java.util.List;
import com.danbro.kill.vo.SmsSeckillSkuRelationVo;

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

    /**
     * 秒杀商品
     *
     * @param killId     商品在缓存中的ID
     * @param randomCode 随机码
     * @param num        秒杀的数量
     * @return
     */
    String killSku(String killId, String randomCode, Integer num);
}
