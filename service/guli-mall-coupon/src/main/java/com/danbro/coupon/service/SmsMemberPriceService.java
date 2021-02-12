package com.danbro.coupon.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.coupon.controller.vo.SmsMemberPriceVo;
import com.danbro.coupon.entity.SmsMemberPrice;


/**
 * 商品会员价格(SmsMemberPrice)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
public interface SmsMemberPriceService extends IService<SmsMemberPrice> {
    /**
     * 添加会员等级的价格
     * @param smsMemberPriceVo 会员等级的价格数据
     * @return 添加完毕后的会员等级的价格数据
     */
    SmsMemberPriceVo insertMemberPrice(SmsMemberPriceVo smsMemberPriceVo);

    /**
     * 批量添加会员等级的价格
     * @param memberPriceVoList 会员等级的价格数据列表
     * @return 添加完毕后的会员等级的价格数据列表
     */
    List<SmsMemberPriceVo> batchInsertMemberPrice(List<SmsMemberPriceVo> memberPriceVoList);
}