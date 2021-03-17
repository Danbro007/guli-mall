package com.danbro.coupon.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.common.enums.PageParam;
import com.danbro.common.utils.Pagination;
import com.danbro.coupon.controller.vo.SmsSeckillSessionVo;
import com.danbro.coupon.entity.SmsSeckillSession;

import java.util.List;


/**
 * 秒杀活动场次(SmsSeckillSession)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
public interface SmsSecKillSessionService extends IService<SmsSeckillSession> {
    Pagination<SmsSeckillSessionVo, SmsSeckillSession> queryPage(PageParam<SmsSeckillSession> pageParam, String key);

    SmsSeckillSessionVo insert(SmsSeckillSessionVo seckillSessionVo);

    SmsSeckillSessionVo update(SmsSeckillSessionVo seckillSessionVo);

    void batchDeleteSession(Long[] ids);

    /**
     * 通过活动场次ID查询到活动的信息
     *
     * @param id 活动场次ID
     * @return 活动信息
     */
    SmsSeckillSessionVo getInfoById(Long id);

    /**
     * 获取最近三天的秒杀活动
     *
     * @return 最近三天的活动场次列表
     */
    List<SmsSeckillSessionVo> getLast3DaySku();
}