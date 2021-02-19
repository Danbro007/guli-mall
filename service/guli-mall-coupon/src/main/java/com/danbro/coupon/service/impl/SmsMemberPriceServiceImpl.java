package com.danbro.coupon.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.ConvertUtils;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.coupon.controller.vo.SmsMemberPriceVo;
import com.danbro.coupon.entity.SmsMemberPrice;
import com.danbro.coupon.mapper.SmsMemberPriceMapper;
import com.danbro.coupon.rpc.clients.UmsMemberLevelClient;
import com.danbro.coupon.service.SmsMemberPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品会员价格(SmsMemberPrice)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Service
public class SmsMemberPriceServiceImpl extends ServiceImpl<SmsMemberPriceMapper, SmsMemberPrice> implements SmsMemberPriceService {
    @Autowired
    UmsMemberLevelClient umsMemberLevelClient;

    @Override
    public SmsMemberPriceVo insertMemberPrice(SmsMemberPriceVo smsMemberPriceVo) {
        return MyCurdUtils.insertOrUpdate(smsMemberPriceVo, this.save(smsMemberPriceVo.convertToEntity()), ResponseCode.INSERT_FAILURE);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<SmsMemberPriceVo> batchInsertMemberPrice(List<SmsMemberPriceVo> memberPriceVoList) {
        // 过滤出会员价格大于 0 的
        List<SmsMemberPrice> memberPriceList = memberPriceVoList.stream().
                filter(memberPrice -> memberPrice.getMemberPrice().compareTo(BigDecimal.ZERO) > 0).
                map(SmsMemberPriceVo::convertToEntity).
                collect(Collectors.toList());
        // Todo 批量保存到 sms_member_price 中
        boolean saveBatch = this.saveBatch(memberPriceList);
        List<SmsMemberPriceVo> priceVoList = ConvertUtils.batchConvert(memberPriceList, SmsMemberPriceVo.class);
        return MyCurdUtils.batchInsertOrUpdate(priceVoList, saveBatch, ResponseCode.INSERT_FAILURE);
    }
}