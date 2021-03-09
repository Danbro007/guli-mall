package com.danbro.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.common.enums.PageParam;
import com.danbro.common.utils.Pagination;
import com.danbro.product.controller.vo.PmsSpuInfoVo;
import com.danbro.product.controller.vo.front.SkuItemVo;
import com.danbro.product.entity.PmsSpuInfo;

import java.util.List;


/**
 * spu信息(PmsSpuInfo)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:55
 */
public interface PmsSpuInfoService extends IService<PmsSpuInfo> {
    /**
     * 添加 Spu 信息
     *
     * @param spuInfoVo spu信息
     */
    void insertSpuInfo(PmsSpuInfoVo spuInfoVo);

    /**
     * 分页查询spu信息
     *
     * @param pageParam 分页条件
     * @param key       关键字
     * @param brandId   品牌ID
     * @param catelogId 分类ID
     * @param status    Spu的状态
     * @return 查询的分页结果
     */
    Pagination<PmsSpuInfoVo, PmsSpuInfo> queryPageByCondition(PageParam<PmsSpuInfo> pageParam, String key, Long brandId, Long catelogId, Integer status);

    /**
     * 商品上线
     *
     * @param spuId 商品spu 的 Id
     */
    void upSpu(Long spuId);

    /**
     * 获取商品详情里 spu 的所有销售属性
     *
     * @param spuId spuID
     * @return 销售属性列表
     */
    List<SkuItemVo.SkuSaleAttrValue> getSaleAttrListBySpuId(Long spuId);

}