package com.danbro.seckill.service;

import com.danbro.common.entity.ResultBean;
import com.danbro.seckill.vo.SmsSeckillSessionVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author Danrbo
 * @Classname CmsFeignService
 * @Description TODO
 * @Date 2021/3/16 12:25
 */
@Component
@FeignClient(value = "service-sms")
public interface SmsFeignService {

    /**
     * 获取最近三天的秒杀活动
     *
     * @return 秒杀活动列表
     */
    @GetMapping("coupon/seckillsession/last/3")
    ResultBean<List<SmsSeckillSessionVo>> getLast3DaysSku();
}
