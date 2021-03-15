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
import com.danbro.coupon.controller.vo.SmsSeckillSessionVo;
import com.danbro.coupon.entity.SmsSeckillSession;
import com.danbro.coupon.mapper.SmsSeckillSessionMapper;
import com.danbro.coupon.service.SmsSeckillSessionService;
import org.springframework.stereotype.Service;

/**
 * 秒杀活动场次(SmsSeckillSession)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Service
public class SmsSeckillSessionServiceImpl extends ServiceImpl<SmsSeckillSessionMapper, SmsSeckillSession> implements SmsSeckillSessionService {

    @Override
    public Pagination<SmsSeckillSessionVo, SmsSeckillSession> queryPage(PageParam<SmsSeckillSession> pageParam, String key) {
        LambdaQueryWrapper<SmsSeckillSession> queryWrapper = new QueryWrapper<SmsSeckillSession>().lambda();
        if (MyStrUtils.isNotEmpty(key)) {
            queryWrapper.like(SmsSeckillSession::getId, key).or().like(SmsSeckillSession::getName, key);
        }
        IPage<SmsSeckillSession> page = this.page(new Query<SmsSeckillSession>().getPage(pageParam), queryWrapper);
        return new Pagination<>(page, SmsSeckillSessionVo.class);
    }

    @Override
    public SmsSeckillSessionVo insert(SmsSeckillSessionVo seckillSessionVo) {
        SmsSeckillSession smsSeckillSession = seckillSessionVo.convertToEntity();
        boolean save = this.save(smsSeckillSession);
        MyCurdUtils.insertOrUpdate(save, ResponseCode.INSERT_FAILURE);
        return ConvertUtils.convert(smsSeckillSession, SmsSeckillSessionVo.class);
    }

    @Override
    public SmsSeckillSessionVo update(SmsSeckillSessionVo seckillSessionVo) {
        SmsSeckillSession smsSeckillSession = seckillSessionVo.convertToEntity();
        boolean save = this.updateById(smsSeckillSession);
        MyCurdUtils.insertOrUpdate(save, ResponseCode.INSERT_FAILURE);
        return ConvertUtils.convert(smsSeckillSession, SmsSeckillSessionVo.class);
    }

    @Override
    public void batchDeleteSession(Long[] ids) {
        MyCurdUtils.delete(this.removeByIds(Arrays.asList(ids)), ResponseCode.DELETE_FAILURE);
    }

    @Override
    public SmsSeckillSessionVo getInfoById(Long id) {
        SmsSeckillSession seckillSession = MyCurdUtils.select(this.getById(id), ResponseCode.NOT_FOUND);
        return ConvertUtils.convert(seckillSession, SmsSeckillSessionVo.class);
    }
}