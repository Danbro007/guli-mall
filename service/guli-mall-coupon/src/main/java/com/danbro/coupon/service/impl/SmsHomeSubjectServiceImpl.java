package com.danbro.coupon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.coupon.entity.SmsHomeSubject;
import com.danbro.coupon.mapper.SmsHomeSubjectMapper;
import com.danbro.coupon.service.SmsHomeSubjectService;
import org.springframework.stereotype.Service;

/**
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】(SmsHomeSubject)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Service
public class SmsHomeSubjectServiceImpl extends ServiceImpl<SmsHomeSubjectMapper, SmsHomeSubject> implements SmsHomeSubjectService {

}