package com.danbro.product.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.entity.ResultBean;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.exceptions.GuliMallException;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.common.utils.MyNumUtils;
import com.danbro.product.controller.vo.*;
import com.danbro.product.controller.vo.spu.Images;
import com.danbro.product.controller.vo.spu.Skus;
import com.danbro.product.entity.PmsSkuImages;
import com.danbro.product.entity.PmsSkuInfo;
import com.danbro.product.mapper.PmsSkuInfoMapper;
import com.danbro.product.rpc.clients.SmsMemberPriceClient;
import com.danbro.product.rpc.clients.SmsSkuFullReductionClient;
import com.danbro.product.rpc.clients.SmsSkuLadderClient;
import com.danbro.product.service.PmsAttrService;
import com.danbro.product.service.PmsSkuImagesService;
import com.danbro.product.service.PmsSkuInfoService;
import com.danbro.product.service.PmsSkuSaleAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * sku信息(PmsSkuInfo)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
@Service
public class PmsSkuInfoServiceImpl extends ServiceImpl<PmsSkuInfoMapper, PmsSkuInfo> implements PmsSkuInfoService {

    @Autowired
    PmsSkuImagesService pmsSkuImagesService;

    @Autowired
    PmsSkuSaleAttrValueService pmsSkuSaleAttrValueService;

    @Autowired
    PmsAttrService pmsAttrService;

    @Autowired
    SmsMemberPriceClient smsMemberPriceClient;

    @Autowired
    SmsSkuLadderClient smsSkuLadderClient;

    @Autowired
    SmsSkuFullReductionClient smsSkuFullReductionClient;


    @Override
    public void batchSaveSkuInfo(List<PmsSkuInfoVo> skuInfoVoList) {
        skuInfoVoList.forEach(sku -> {
            // Todo 到 pms_sku_info 保存
            PmsSkuInfo pmsSkuInfo = sku.setSkuDefaultImg(findDefaultImage(sku.getImages())).convertToEntity();
            boolean save = this.save(pmsSkuInfo);
            sku.setSkuId(pmsSkuInfo.getSkuId());
            MyCurdUtils.batchInsertOrUpdate(skuInfoVoList, save, ResponseCode.INSERT_FAILURE);
            // Todo 到 pms_sku_image 保存
            List<PmsSkuImagesVo> skuImagesVoList = sku.getImages().stream().map(image -> image.setSkuId(sku.getSkuId())).
                    collect(Collectors.toList());
            pmsSkuImagesService.batchSaveSkuImages(skuImagesVoList);
            // Todo 到 pms_sku_sale_attr_value 保存销售属性s
            List<PmsSkuSaleAttrValueVo> attrValueVos = sku.getAttr().stream().map(saleAttr -> saleAttr.setSkuId(sku.getSkuId())).collect(Collectors.toList());
            pmsSkuSaleAttrValueService.batchSaveSaleAttrValue(attrValueVos);
            // Todo 远程调用 保存到 sms_member_price
            sku.getMemberPrice().stream().filter(memberPrice -> memberPrice.getMemberPrice().compareTo(new BigDecimal(0)) > 0).forEach(memberPrice -> memberPrice.setSkuId(sku.getSkuId()));
            MyCurdUtils.rpcInsertOrUpdate(smsMemberPriceClient.batchInsertMemberPrice(sku.getMemberPrice()));
            // Todo 远程调用 保存到 sms_sku_ladder(打折)
            // 只添加满足打折的件数和折扣数大于 0 的
            SmsSkuLadderVo smsSkuLadderVo = buildSkuLadder(sku);
            if (smsSkuLadderVo.getFullCount() > 0
                    && MyNumUtils.between(smsSkuLadderVo.getDiscount(), BigDecimal.ZERO, BigDecimal.ONE)) {
                MyCurdUtils.rpcInsertOrUpdate(smsSkuLadderClient.insertSkuLadder(smsSkuLadderVo));
            }
            // Todo 远程调用 保存到 sms_sku_full_reduction （满减）
            // 只添加满减价格和优惠价格大于 0 的
            SmsSkuFullReductionVo skuFullReductionVo = buildSkuFullReduction(sku);
            if (skuFullReductionVo.getFullPrice().compareTo(new BigDecimal(0)) > 0 && skuFullReductionVo.getReducePrice().compareTo(new BigDecimal(0)) > 0) {
                MyCurdUtils.rpcInsertOrUpdate(smsSkuFullReductionClient.insertSkuFullReduction(skuFullReductionVo));
            }
        });
    }

    /**
     * 查出 sku 的默认图片
     *
     * @param imagesList 图片对象列表
     * @return 默认图片的地址
     */
    private String findDefaultImage(List<PmsSkuImagesVo> imagesList) {
        List<PmsSkuImagesVo> images = imagesList.stream().filter(PmsSkuImagesVo::getDefaultImg).collect(Collectors.toList());
        if (images.size() == 1) {
            return images.get(0).getImgUrl();
        }
        return "";
    }

    /**
     * 构建 skuLadderVo 对象
     *
     * @param sku 构建的数据
     * @return 构建完毕的对象
     */
    private SmsSkuLadderVo buildSkuLadder(PmsSkuInfoVo sku) {
        return SmsSkuLadderVo.builder().build().
                setDiscount(sku.getDiscount()).
                setFullCount(sku.getFullCount()).
                setSkuId(sku.getSkuId()).
                setPrice(sku.getPrice()).
                setAddOther(sku.getCountStatus());
    }

    /**
     * 构建 SkuFullReductionVo 对象
     *
     * @param sku 构建的数据
     * @return 构建完毕的对象
     */
    private SmsSkuFullReductionVo buildSkuFullReduction(PmsSkuInfoVo sku) {
        return SmsSkuFullReductionVo.builder().build().
                setSkuId(sku.getSkuId()).
                setFullPrice(sku.getFullPrice()).
                setReducePrice(sku.getReducePrice()).
                setAddOther(sku.getPriceStatus());
    }
}