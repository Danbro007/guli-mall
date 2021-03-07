package com.danbro.order.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.ConvertUtils;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.common.utils.MyStrUtils;
import com.danbro.order.controller.vo.PmsSpuInfoDescVo;
import com.danbro.order.entity.PmsSpuInfoDesc;
import com.danbro.order.mapper.PmsSpuInfoDescMapper;
import com.danbro.order.service.PmsSpuInfoDescService;
import org.springframework.stereotype.Service;

/**
 * spu信息介绍(PmsSpuInfoDesc)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:55
 */
@Service
public class PmsSpuInfoDescServiceImpl extends ServiceImpl<PmsSpuInfoDescMapper, PmsSpuInfoDesc> implements PmsSpuInfoDescService {

    @Override
    public PmsSpuInfoDescVo saveSpuInfoDesc(List<String> images, Long spuId) {
        // 图片之间用 ， 间隔处理
        PmsSpuInfoDesc pmsSpuInfoDesc = new PmsSpuInfoDesc().setDecript(MyStrUtils.join(",", images)).setSpuId(spuId);
        return MyCurdUtils.insertOrUpdate(PmsSpuInfoDescVo.builder().build().convertToVo(pmsSpuInfoDesc), this.save(pmsSpuInfoDesc), ResponseCode.INSERT_FAILURE);
    }

    @Override
    public PmsSpuInfoDescVo getSpuDescBySpuId(Long spuId) {
        PmsSpuInfoDesc pmsSpuInfoDesc = MyCurdUtils.select(this.getById(spuId), ResponseCode.NOT_FOUND);
        return ConvertUtils.convert(pmsSpuInfoDesc, PmsSpuInfoDescVo.class);
    }
}