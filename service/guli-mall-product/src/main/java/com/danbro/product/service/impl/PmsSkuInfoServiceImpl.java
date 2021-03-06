package com.danbro.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.PageParam;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.*;
import com.danbro.product.controller.vo.*;
import com.danbro.product.controller.vo.front.SkuItemVo;
import com.danbro.product.controller.vo.front.SmsSeckillSkuRelationVo;
import com.danbro.product.entity.PmsSkuInfo;
import com.danbro.product.mapper.PmsSkuInfoMapper;
import com.danbro.product.rpc.clients.*;
import com.danbro.product.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

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

    @Autowired
    PmsSkuInfoService pmsSkuInfoService;

    @Autowired
    PmsSpuInfoDescService pmsSpuInfoDescService;

    @Autowired
    PmsAttrGroupService pmsAttrGroupService;

    @Autowired
    PmsProductAttrValueService pmsProductAttrValueService;

    @Autowired
    PmsSpuInfoService pmsSpuInfoService;

    @Autowired
    WmsWareSkuClient wmsWareSkuClient;

    @Autowired
    ThreadPoolExecutor threadPoolExecutor;

    @Autowired
    PmsSpuImagesService pmsSpuImagesService;

    @Autowired
    SecKillFeignService secKillFeignService;

    @Override
    public void batchSaveSkuInfo(List<PmsSkuInfoVo> skuInfoVoList) {
        skuInfoVoList.forEach(sku -> {
            // 1、到 pms_sku_info 保存
            PmsSkuInfo pmsSkuInfo = sku.setSkuDefaultImg(findDefaultImage(sku.getImages())).convertToEntity();
            boolean save = this.save(pmsSkuInfo);
            sku.setSkuId(pmsSkuInfo.getSkuId());
            MyCurdUtils.batchInsertOrUpdate(skuInfoVoList, save, ResponseCode.INSERT_FAILURE);
            // 2、 到 pms_sku_image 保存，过滤掉 url 为空的
            List<PmsSkuImagesVo> skuImagesVoList = sku.getImages().stream().filter(image -> MyStrUtils.isNotEmpty(image.getImgUrl())).map(image -> image.setSkuId(sku.getSkuId())).
                    collect(Collectors.toList());
            if (MyCollectionUtils.isNotEmpty(skuImagesVoList)) {
                pmsSkuImagesService.batchSaveSkuImages(skuImagesVoList);
            }
            // 3、 到 pms_sku_sale_attr_value 保存销售属性
            if (MyCollectionUtils.isNotEmpty(sku.getMemberPrice())) {
                List<PmsSkuSaleAttrValueVo> attrValueVos = sku.getAttr().stream().map(saleAttr -> saleAttr.setSkuId(sku.getSkuId())).collect(Collectors.toList());
                pmsSkuSaleAttrValueService.batchSaveSaleAttrValue(attrValueVos);
            }
            // 4、 远程调用 保存到 sms_member_price
            // 过滤出会员价格大于 0 的
            List<SmsMemberPriceVo> memberPriceVos = sku.getMemberPrice().stream().filter(memberPrice -> memberPrice.getMemberPrice().compareTo(BigDecimal.ZERO) > 0).collect(Collectors.toList());
            if (MyCollectionUtils.isNotEmpty(memberPriceVos)) {
                memberPriceVos.forEach(e -> e.setSkuId(sku.getSkuId()));
                MyCurdUtils.rpcResultHandle(smsMemberPriceClient.batchInsertMemberPrice(memberPriceVos));
            }
            // 5、 远程调用 保存到 sms_sku_ladder(打折)
            // 只添加满足打折的件数和折扣数大于 0 的
            SmsSkuLadderVo smsSkuLadderVo = buildSkuLadder(sku);
            if (MyObjectUtils.isNotNull(smsSkuLadderVo)
                    && smsSkuLadderVo.getFullCount() > 0
                    && MyNumUtils.between(smsSkuLadderVo.getDiscount(), BigDecimal.ZERO, BigDecimal.ONE)) {
                MyCurdUtils.rpcResultHandle(smsSkuLadderClient.insertSkuLadder(smsSkuLadderVo));
            }
            // 5、远程调用 保存到 sms_sku_full_reduction （满减）
            // 只添加满减价格和优惠价格大于 0 的
            SmsSkuFullReductionVo skuFullReductionVo = buildSkuFullReduction(sku);
            if (MyObjectUtils.isNotNull(skuFullReductionVo) &&
                    skuFullReductionVo.getFullPrice().compareTo(new BigDecimal(0)) > 0 &&
                    skuFullReductionVo.getReducePrice().compareTo(new BigDecimal(0)) > 0) {
                MyCurdUtils.rpcResultHandle(smsSkuFullReductionClient.insertSkuFullReduction(skuFullReductionVo));
            }
        });
    }

    @Override
    public Pagination<PmsSkuInfoVo, PmsSkuInfo> queryPageByCondition(PageParam<PmsSkuInfo> pageParam, String key, Long brandId, Long catelogId, BigDecimal min, BigDecimal max) {
        LambdaQueryWrapper<PmsSkuInfo> queryWrapper = new QueryWrapper<PmsSkuInfo>().lambda();
        // 模糊查询
        if (MyStrUtils.isNotEmpty(key)) {
            queryWrapper.like(PmsSkuInfo::getSkuName, key).or().
                    like(PmsSkuInfo::getSkuId, key).or().
                    like(PmsSkuInfo::getSkuDesc, key).or().
                    like(PmsSkuInfo::getSkuTitle, key).or().
                    like(PmsSkuInfo::getSkuTitle, key).or().
                    eq(PmsSkuInfo::getSkuId, key);
        }
        if (MyObjectUtils.isNotNull(brandId) && brandId > 0) {
            queryWrapper.eq(PmsSkuInfo::getBrandId, brandId);
        }
        if (MyObjectUtils.isNotNull(catelogId) && catelogId > 0) {
            queryWrapper.eq(PmsSkuInfo::getCatalogId, catelogId);
        }
        // 最低价和最高价
        if (MyObjectUtils.isNotNull(min) && MyObjectUtils.isNotNull(max)) {
            // 同时为0则不进行查询
            if (min.compareTo(BigDecimal.ZERO) != 0 && max.compareTo(BigDecimal.ZERO) != 0) {
                queryWrapper.between(PmsSkuInfo::getPrice, min, max);
            }
        }
        return new Pagination<>(this.page(new Query<PmsSkuInfo>().getPage(pageParam), queryWrapper), PmsSkuInfoVo.class);
    }

    @Override
    public PmsSkuInfoVo getSkuInfoById(Long skuId) {
        PmsSkuInfo pmsSkuInfo = MyCurdUtils.select(this.getById(skuId), ResponseCode.NOT_FOUND);
        PmsSkuInfoVo skuInfoVo = ConvertUtils.convert(pmsSkuInfo, PmsSkuInfoVo.class);
        List<PmsSkuImagesVo> skuImages = pmsSkuImagesService.getSkuImageBySkuId(skuId);
        if (MyObjectUtils.isNotNull(skuImages)) {
            skuInfoVo.setImages(skuImages);
            String defaultImage = findDefaultImage(skuImages);
            if (MyStrUtils.isNotEmpty(defaultImage)) {
                skuInfoVo.setSkuDefaultImg(defaultImage);
            }
        }
        List<PmsSkuSaleAttrValueVo> saleValueList = pmsSkuSaleAttrValueService.getSaleAttrValueListBySkuId(skuId);
        if (MyCollectionUtils.isNotEmpty(saleValueList)) {
            skuInfoVo.setAttr(saleValueList);
        }
        return skuInfoVo;
    }

    @Override
    public List<PmsSkuInfoVo> getSkuInfoListBySpuId(Long spuId) {
        List<PmsSkuInfo> pmsSkuInfoList = MyCurdUtils.selectList(this.list(new QueryWrapper<PmsSkuInfo>().lambda().eq(PmsSkuInfo::getSpuId, spuId)), ResponseCode.NOT_FOUND);
        return ConvertUtils.batchConvert(pmsSkuInfoList, PmsSkuInfoVo.class);
    }

    // Todo 商品详情缓存起来
    @Override
    public SkuItemVo getItemBySkuId(Long skuId) throws ExecutionException, InterruptedException {
        // 使用异步编排
        SkuItemVo skuItemVo = new SkuItemVo();
        // 到 pms_sku_image 查询出 sku 的图片
        CompletableFuture<Void> skuImageFuture = CompletableFuture.runAsync(() -> {
            List<PmsSkuImagesVo> pmsSkuImageList = pmsSkuImagesService.getSkuImageBySkuId(skuId);
            skuItemVo.setImages(pmsSkuImageList);
        }, threadPoolExecutor);
        // 查询出 sku 的 spu 信息
        CompletableFuture<PmsSkuInfoVo> pmsSkuInfoFuture = CompletableFuture.supplyAsync(() -> {
            PmsSkuInfoVo skuInfoVo = pmsSkuInfoService.getSkuInfoById(skuId);
            skuItemVo.setInfo(skuInfoVo);
            return skuInfoVo;
        }, threadPoolExecutor);
        // 查询出 sku 对应的 spu 的相关基本属性
        // 查询分类下的属性分组
        CompletableFuture<Void> baseAttrGroupFuture = pmsSkuInfoFuture.thenAcceptAsync(pmsSkuInfoVo -> {
            List<SkuItemVo.SpuAttrGroupVo> baseAttrGroupList = pmsProductAttrValueService.getBaseAttrBySpuId(pmsSkuInfoVo.getSpuId());
            skuItemVo.setGroupAttrs(baseAttrGroupList);
        }, threadPoolExecutor);
        // 查询出 sku 对应的 spu 介绍图(pms_spu_info_desc)
        CompletableFuture<Void> spuInfoFuture = pmsSkuInfoFuture.thenAcceptAsync(pmsSkuInfoVo -> {
            PmsSpuInfoDescVo pmsSpuInfoDescVo = pmsSpuInfoDescService.getSpuDescBySpuId(pmsSkuInfoVo.getSpuId());
            skuItemVo.setDesc(pmsSpuInfoDescVo);
        }, threadPoolExecutor);
        // 查询出 sku 的所有销售属性
        CompletableFuture<Void> skuSaleAttrFuture = pmsSkuInfoFuture.thenAcceptAsync(pmsSkuInfoVo -> {
            List<SkuItemVo.SkuSaleAttrValue> saleAttrValueList = pmsSpuInfoService.getSaleAttrListBySpuId(pmsSkuInfoVo.getSpuId());
            skuItemVo.setSaleAttr(saleAttrValueList);
        }, threadPoolExecutor);
        //查询出是否有库存
        CompletableFuture<Void> skuStockFuture = pmsSkuInfoFuture.thenAcceptAsync(pmsSkuInfoVo -> {
            Boolean hasStock = MyCurdUtils.rpcResultHandle(wmsWareSkuClient.hasStock(pmsSkuInfoVo.getSpuId()), true);
            skuItemVo.setHasStock(hasStock);
        }, threadPoolExecutor);
        CompletableFuture<Void> secKillRelationFuture = pmsSkuInfoFuture.thenAcceptAsync(pmsSkuInfoVo -> {
            SmsSeckillSkuRelationVo skuRelationVo = MyCurdUtils.rpcResultHandle(secKillFeignService.getSecKillRelationInfo(pmsSkuInfoVo.getSkuId()), false);
            if (skuRelationVo != null) {
                skuItemVo.setSeckillSkuVo(skuRelationVo);
            }
        }, threadPoolExecutor);
        CompletableFuture.allOf(skuImageFuture, baseAttrGroupFuture, spuInfoFuture, skuSaleAttrFuture, skuStockFuture, secKillRelationFuture).get();
        return skuItemVo;
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
        SmsSkuLadderVo skuLadderVo = SmsSkuLadderVo.builder().build();
        MyBeanUtils.copyProperties(sku, skuLadderVo);
        return skuLadderVo;
    }

    /**
     * 构建 SkuFullReductionVo 对象
     *
     * @param sku 构建的数据
     * @return 构建完毕的对象
     */
    private SmsSkuFullReductionVo buildSkuFullReduction(PmsSkuInfoVo sku) {
        SmsSkuFullReductionVo skuFullReductionVo = SmsSkuFullReductionVo.builder().build();
        MyBeanUtils.copyProperties(sku, skuFullReductionVo);
        return skuFullReductionVo;
    }
}