package com.danbro.product.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.entity.ResultBean;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.product.controller.vo.PmsProductAttrValueVo;
import com.danbro.product.controller.vo.PmsSpuInfoVo;
import com.danbro.product.controller.vo.SmsSpuBondsVo;
import com.danbro.product.controller.vo.spu.Bounds;
import com.danbro.product.controller.vo.spu.Spu;
import com.danbro.product.entity.PmsSpuInfo;
import com.danbro.product.mapper.PmsSpuInfoMapper;
import com.danbro.product.rpc.clients.SmsSpuBondsClient;
import com.danbro.product.service.PmsProductAttrValueService;
import com.danbro.product.service.PmsSkuInfoService;
import com.danbro.product.service.PmsSpuImagesService;
import com.danbro.product.service.PmsSpuInfoDescService;
import com.danbro.product.service.PmsSpuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * spu信息(PmsSpuInfo)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:55
 */
@Service
public class PmsSpuInfoServiceImpl extends ServiceImpl<PmsSpuInfoMapper, PmsSpuInfo> implements PmsSpuInfoService {
    @Autowired
    PmsSpuInfoDescService pmsSpuInfoDescService;

    @Autowired
    PmsSpuImagesService pmsSpuImagesService;

    @Autowired
    PmsProductAttrValueService pmsProductAttrValueService;

    @Autowired
    PmsSkuInfoService pmsSkuInfoService;

    @Autowired
    SmsSpuBondsClient smsSpuBondsClient;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertSpuInfo(PmsSpuInfoVo pmsSpuInfoVo) {
        //1、Todo 添加 spu 的基本信息 pms_spu_info
        PmsSpuInfo spuInfo = pmsSpuInfoVo.convertToEntity();
        boolean save = this.save(spuInfo);
        MyCurdUtils.insertOrUpdate(pmsSpuInfoVo.convertToVo(spuInfo), save, ResponseCode.INSERT_FAILURE);
        // 2、Todo 添加商品介绍图片 pms_spu_info_desc
        pmsSpuInfoDescService.saveSpuInfoDesc(pmsSpuInfoVo.getDecript(), pmsSpuInfoVo.getId());
        // 3. Todo 到 pms_spu_image 保存图片
        pmsSpuImagesService.batchSave(pmsSpuInfoVo.getImages(), pmsSpuInfoVo.getId());
        // 4、Todo 到 sms_spu_bonds 保存(远程调用)
        SmsSpuBondsVo bounds = pmsSpuInfoVo.getBounds();
        bounds.setSpuId(spuInfo.getId());
        MyCurdUtils.rpcInsertOrUpdate(smsSpuBondsClient.insertSpuBonds(bounds));
        // 5、Todo 到 pms_product_attr_value 保存
        List<PmsProductAttrValueVo> attrValueList = pmsSpuInfoVo.getBaseAttrs().stream().map(
                attr -> PmsProductAttrValueVo.builder().build().
                        setAttrId(attr.getAttrId()).
                        setAttrValue(attr.getAttrValues()).
                        setSpuId(pmsSpuInfoVo.getId()).
                        setQuickShow(attr.getShowDesc())).
                collect(Collectors.toList());
        pmsProductAttrValueService.batchSave(attrValueList);
        // 6、Todo 保存sku信息
        pmsSpuInfoVo.getSkus().forEach(sku -> sku.setBrandId(pmsSpuInfoVo.getBrandId()).
                setCatalogId(pmsSpuInfoVo.getCatalogId()).
                setSkuDesc(pmsSpuInfoVo.getSpuDescription()).setSpuId(pmsSpuInfoVo.getId()));
        pmsSkuInfoService.batchSaveSkuInfo(pmsSpuInfoVo.getSkus());
    }
}