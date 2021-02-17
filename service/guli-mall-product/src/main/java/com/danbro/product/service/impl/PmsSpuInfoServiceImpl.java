package com.danbro.product.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.PageParam;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.enums.pms.ProductPublishStatus;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.common.utils.MyObjectUtils;
import com.danbro.common.utils.MyStrUtils;
import com.danbro.common.utils.Pagination;
import com.danbro.common.utils.Query;
import com.danbro.product.controller.esModel.ProductAttrEsModel;
import com.danbro.product.controller.esModel.ProductSkuInfoEsModel;
import com.danbro.product.controller.vo.*;
import com.danbro.product.entity.PmsSpuInfo;
import com.danbro.product.mapper.PmsSpuInfoMapper;
import com.danbro.product.rpc.clients.SearchClient;
import com.danbro.product.rpc.clients.SmsSpuBondsClient;
import com.danbro.product.rpc.clients.WmsWareSkuClient;
import com.danbro.product.service.*;
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

    @Autowired
    PmsBrandService pmsBrandService;

    @Autowired
    PmsCategoryService pmsCategoryService;

    @Autowired
    WmsWareSkuClient wmsWareSkuClient;

    @Autowired
    PmsAttrService pmsAttrService;

    @Autowired
    SearchClient searchClient;

    @Autowired
    PmsSpuInfoService pmsSpuInfoService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertSpuInfo(PmsSpuInfoVo pmsSpuInfoVo) {
        //1、添加 spu 的基本信息 pms_spu_info
        PmsSpuInfo spuInfo = pmsSpuInfoVo.convertToEntity();
        boolean save = this.save(spuInfo);
        MyCurdUtils.insertOrUpdate(pmsSpuInfoVo.convertToVo(spuInfo), save, ResponseCode.INSERT_FAILURE);
        // 2、添加商品介绍图片 pms_spu_info_desc
        pmsSpuInfoDescService.saveSpuInfoDesc(pmsSpuInfoVo.getDecript(), pmsSpuInfoVo.getId());
        // 3. 到 pms_spu_image 保存图片
        pmsSpuImagesService.batchSave(pmsSpuInfoVo.getImages(), pmsSpuInfoVo.getId());
        // 4、 到 sms_spu_bonds 保存(远程调用)
        SmsSpuBondsVo bounds = pmsSpuInfoVo.getBounds();
        bounds.setSpuId(spuInfo.getId());
        MyCurdUtils.rpcResultHandle(smsSpuBondsClient.insertSpuBonds(bounds));
        // 5、 到 pms_product_attr_value 保存
        List<PmsProductAttrValueVo> attrValueList = pmsSpuInfoVo.getBaseAttrs().stream().map(
                attr -> PmsProductAttrValueVo.builder().build().
                        setAttrId(attr.getAttrId()).
                        setAttrValue(attr.getAttrValues()).
                        setSpuId(pmsSpuInfoVo.getId()).
                        setQuickShow(attr.getShowDesc())).
                collect(Collectors.toList());
        pmsProductAttrValueService.batchSave(attrValueList);
        // 6、 保存sku信息
        pmsSpuInfoVo.getSkus().forEach(sku -> sku.setBrandId(pmsSpuInfoVo.getBrandId()).
                setCatalogId(pmsSpuInfoVo.getCatalogId()).
                setSkuDesc(pmsSpuInfoVo.getSpuDescription()).setSpuId(pmsSpuInfoVo.getId()));
        pmsSkuInfoService.batchSaveSkuInfo(pmsSpuInfoVo.getSkus());
    }

    @Override
    public Pagination<PmsSpuInfoVo, PmsSpuInfo> queryPageByCondition(PageParam<PmsSpuInfo> pageParam, String key, Long brandId, Long catelogId, Integer status) {
        LambdaQueryWrapper<PmsSpuInfo> queryWrapper = new QueryWrapper<PmsSpuInfo>().lambda();
        if (MyStrUtils.isNotEmpty(key)) {
            queryWrapper.like(PmsSpuInfo::getSpuName, key).or().like(PmsSpuInfo::getId, key);
        }
        if (MyObjectUtils.isNotNull(brandId) && brandId > 0) {
            queryWrapper.eq(PmsSpuInfo::getBrandId, brandId);
        }
        if (MyObjectUtils.isNotNull(catelogId) && catelogId > 0) {
            queryWrapper.eq(PmsSpuInfo::getCatalogId, catelogId);
        }
        if (MyObjectUtils.isNotNull(status)) {
            queryWrapper.eq(PmsSpuInfo::getPublishStatus, status);
        }
        return new Pagination<>(this.page(new Query<PmsSpuInfo>().getPage(pageParam), queryWrapper), PmsSpuInfoVo.class);
    }

    @Override
    public void upSpu(Long spuId) {
        // 1、查询出 spu 的信息
        PmsSpuInfo pmsSpuInfo = MyCurdUtils.select(this.getById(spuId), ResponseCode.NOT_FOUND);
        // 2、查询出spu所属的所有sku
        List<PmsSkuInfoVo> skuInfoVoList = pmsSkuInfoService.getSkuInfoListBySpuId(spuId);
        // 3、 查询出品牌信息
        PmsBrandVo brandInfo = pmsBrandService.getBrandInfoById(pmsSpuInfo.getBrandId());
        // 4、 查询出分类信息
        PmsCategoryVo categoryInfo = pmsCategoryService.getCategoryInfo(pmsSpuInfo.getCatalogId(), true);
        // 5、查询出spu相关的规格属性值ID
        List<PmsProductAttrValueVo> productAttrValueList = pmsProductAttrValueService.getAttrValueListBySpuId(spuId);
        // 6、通过找到的属性值找出属性ID，然后找出被设置为能被检索的基本属性，并查询出属性信息。
        List<PmsAttrBaseInfoVo> attrListWithCanShow = pmsAttrService.getBaseAttrListWithCanShow(productAttrValueList.stream().map(PmsProductAttrValueVo::getAttrId).collect(Collectors.toList()));
        // 7、把 productAttrValueList 里不能被检索的属性值过滤掉
        HashSet<Long> attrIdSet = attrListWithCanShow.stream().map(PmsAttrBaseInfoVo::getAttrId).collect(Collectors.toCollection(HashSet::new));
        List<ProductAttrEsModel> attrEsModels = productAttrValueList.stream().filter(attr ->
                attrIdSet.contains(attr.getAttrId())
        ).map(attr -> {
            ProductAttrEsModel attrEsModel = ProductAttrEsModel.builder().build();
            MyBeanUtils.copyProperties(attr, attrEsModel);
            return attrEsModel;
        }).collect(Collectors.toList());
        // 7、建立 es 的 skuInfo 数据模型对象
        List<ProductSkuInfoEsModel> esProductSkuModelList = skuInfoVoList.stream().map(skuInfoVo -> {
            // 8.1 sku信息
            ProductSkuInfoEsModel skuInfoEsModel = ProductSkuInfoEsModel.builder().build();
            MyBeanUtils.copyProperties(skuInfoVo, skuInfoEsModel);
            // 8.2 品牌信息
            skuInfoEsModel.setBrandImg(brandInfo.getLogo()).setBrandId(brandInfo.getBrandId()).setBrandName(brandInfo.getName());
            // 8.3 分类信息
            skuInfoEsModel.setCatalogName(categoryInfo.getName()).setCatalogId(categoryInfo.getCatId());
            // 8.4 rpc 查询当前的sku还有没有库存,如果是服务超时则默认设置为有库存
            Boolean hasStock = MyCurdUtils.rpcResultHandle(wmsWareSkuClient.hasStock(skuInfoVo.getSkuId()), false);
            hasStock = true;
            skuInfoEsModel.setHasStock(hasStock);
            // Todo 8.5商品搜索热度 我这里暂定为 0
            skuInfoEsModel.setHotScore(0L);
            skuInfoEsModel.setAttrs(attrEsModels);
            return skuInfoEsModel;
        }).collect(Collectors.toList());
        // Todo 10、发送给ES存储
        MyCurdUtils.rpcResultHandle(searchClient.batchInsert(esProductSkuModelList));
        // 11、发送成功则把spu状态设置为上架状态
        pmsSpuInfo.setPublishStatus(ProductPublishStatus.PUT_ON);
        MyCurdUtils.insertOrUpdate(pmsSpuInfoService.updateById(pmsSpuInfo), ResponseCode.UPDATE_FAILURE);
    }
}