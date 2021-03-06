package com.danbro.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.common.enums.PageParam;
import com.danbro.common.utils.Pagination;
import com.danbro.product.controller.vo.PmsSkuInfoVo;
import com.danbro.product.controller.vo.front.SkuItemVo;
import com.danbro.product.entity.PmsSkuInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * sku信息(PmsSkuInfo)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
public interface PmsSkuInfoService extends IService<PmsSkuInfo> {
    /**
     * 批量添加Sku
     *
     * @param skuInfoVoList 批量的skuInfoVo
     */
    void batchSaveSkuInfo(List<PmsSkuInfoVo> skuInfoVoList);

    /**
     * 分页查询 sku 信息
     *
     * @param pageParam 分页条件
     * @param key       关键字
     * @param brandId   品牌ID
     * @param catelogId 分类ID
     * @param min       最低价格
     * @param max       最高价格
     * @return 分页查询的结果
     */
    Pagination<PmsSkuInfoVo, PmsSkuInfo> queryPageByCondition(PageParam<PmsSkuInfo> pageParam, String key, Long brandId, Long catelogId, BigDecimal min, BigDecimal max);

    /**
     * 获取sku的详细信息
     *
     * @param skuId skuID
     * @return sku的详细信息
     */
    PmsSkuInfoVo getSkuInfoById(Long skuId);

    /**
     * 通过 spu 获取到所有的 sku 信息
     *
     * @param spuId spuId
     * @return sku信息列表
     */
    List<PmsSkuInfoVo> getSkuInfoListBySpuId(Long spuId);

    /**
     * 查询出sku的相关信息，并进行封装。
     *
     * @param skuId skuId
     * @return 封装信息
     */
    SkuItemVo getItemBySkuId(Long skuId) throws ExecutionException, InterruptedException;
}