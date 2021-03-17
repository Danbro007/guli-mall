package com.danbro.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.PageParam;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.*;
import com.danbro.coupon.controller.vo.SmsSeckillSessionVo;
import com.danbro.coupon.controller.vo.SmsSeckillSkuRelationVo;
import com.danbro.coupon.entity.SmsSeckillSession;
import com.danbro.coupon.mapper.SmsSeckillSessionMapper;
import com.danbro.coupon.service.SmsSecKillSessionService;
import com.danbro.coupon.service.SmsSeckillSkuRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 秒杀活动场次(SmsSeckillSession)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Service
public class SmsSecKillSessionServiceImpl extends ServiceImpl<SmsSeckillSessionMapper, SmsSeckillSession> implements SmsSecKillSessionService {


    @Autowired
    SmsSeckillSkuRelationService smsSeckillSkuRelationService;

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

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

    @Override
    public List<SmsSeckillSessionVo> getLast3DaySku() {
        LocalDate now = LocalDate.now();
        String startTime = getStartTime(now);
        String endTime = getEndTime(now);
        // 查询秒杀活动在最近三天，并且状态为上线的活动。
        List<SmsSeckillSession> secKillSessions = this.list(new QueryWrapper<SmsSeckillSession>().lambda().
                gt(SmsSeckillSession::getStartTime, startTime).
                lt(SmsSeckillSession::getEndTime, endTime).
                eq(SmsSeckillSession::getStatus, true));
        // 查询每个秒杀活动对应的秒杀商品关系
        if (MyCollectionUtils.isNotEmpty(secKillSessions)) {
            return secKillSessions.stream().map(session -> {
                SmsSeckillSessionVo smsSeckillSessionVo = SmsSeckillSessionVo.builder().build().convertToVo(session);
                List<SmsSeckillSkuRelationVo> relations = smsSeckillSkuRelationService.getRelationByPromotionSessionId(smsSeckillSessionVo.getId());
                if (MyCollectionUtils.isNotEmpty(relations)) {
                    smsSeckillSessionVo.setRelationList(relations);
                }
                return smsSeckillSessionVo;
            }).collect(Collectors.toList());
        }
        return null;
    }


    private String getStartTime(LocalDate now) {
        LocalTime min = LocalTime.MIN;
        LocalDateTime start = LocalDateTime.of(now, min);
        return start.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }


    private String getEndTime(LocalDate now) {
        LocalDate localDate = now.plusDays(2);
        LocalTime max = LocalTime.MAX;
        LocalDateTime end = LocalDateTime.of(localDate, max);
        return end.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }


}