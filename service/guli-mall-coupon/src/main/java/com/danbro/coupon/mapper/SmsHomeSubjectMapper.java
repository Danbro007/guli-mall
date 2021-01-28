package com.danbro.coupon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.danbro.coupon.entity.SmsHomeSubject;
import org.apache.ibatis.annotations.Mapper;

/**
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】(SmsHomeSubject)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Mapper
public interface SmsHomeSubjectMapper extends BaseMapper<SmsHomeSubject> {

}