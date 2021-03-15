package com.danbro.coupon.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.common.enums.PageParam;
import com.danbro.common.utils.Pagination;
import com.danbro.coupon.controller.vo.SmsSeckillSessionVo;
import com.danbro.coupon.entity.SmsSeckillSession;


/**
 * 秒杀活动场次(SmsSeckillSession)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
public interface SmsSeckillSessionService extends IService<SmsSeckillSession> {
    Pagination<SmsSeckillSessionVo, SmsSeckillSession> queryPage(PageParam<SmsSeckillSession> pageParam, String key);

    SmsSeckillSessionVo insert(SmsSeckillSessionVo seckillSessionVo);

    SmsSeckillSessionVo update(SmsSeckillSessionVo seckillSessionVo);

    void batchDeleteSession(Long[] ids);

    SmsSeckillSessionVo getInfoById(Long id);
}