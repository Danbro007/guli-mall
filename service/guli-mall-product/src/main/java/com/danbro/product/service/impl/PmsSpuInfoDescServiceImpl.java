package com.danbro.product.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.common.utils.MyStrUtils;
import com.danbro.product.controller.vo.PmsSpuInfoDescVo;
import com.danbro.product.entity.PmsSpuInfoDesc;
import com.danbro.product.mapper.PmsSpuInfoDescMapper;
import com.danbro.product.service.PmsSpuInfoDescService;
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
}