package com.danbro.coupon.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.coupon.entity.SmsCouponHistory;
import com.danbro.coupon.mapper.SmsCouponHistoryMapper;
import com.danbro.coupon.service.SmsCouponHistoryService;
import org.springframework.stereotype.Service;

/**
 * 优惠券领取历史记录(SmsCouponHistory)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Service
public class SmsCouponHistoryServiceImpl extends ServiceImpl<SmsCouponHistoryMapper, SmsCouponHistory> implements SmsCouponHistoryService {

}