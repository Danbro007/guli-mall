package com.danbro.ware.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.common.enums.PageParam;
import com.danbro.common.utils.Pagination;
import com.danbro.ware.controller.vo.OrderToResponseVo;
import com.danbro.ware.controller.vo.WmsLockStockResultVo;
import com.danbro.ware.controller.vo.WmsWareSkuVo;
import com.danbro.ware.entity.WmsWareOrderTaskDetail;
import com.danbro.ware.entity.WmsWareSku;

import java.util.List;


/**
 * 商品库存(WmsWareSku)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:06:16
 */
public interface WmsWareSkuService extends IService<WmsWareSku> {
    /**
     * 分页查询sku的库存
     *
     * @param pageParam 分页条件
     * @param skuId     商品Id
     * @param wareId    仓库ID
     * @return 分页查询的结果
     */
    Pagination<WmsWareSkuVo, WmsWareSku> queryPageByCondition(PageParam<WmsWareSku> pageParam, Long skuId, Long wareId);

    /**
     * 添加商品库存信息
     *
     * @param wmsWareSkuVo 要添加的商品库存信息
     * @return 添加完毕的商品库存信息
     */
    WmsWareSkuVo insertWareSku(WmsWareSkuVo wmsWareSkuVo);

    /**
     * 更细商品库存信息
     *
     * @param wmsWareSkuVo 要更新的商品库存信息
     * @return 更新完毕的商品库存信息
     */
    WmsWareSkuVo updateWareSku(WmsWareSkuVo wmsWareSkuVo);

    /**
     * 获取商品库存的详细信息
     *
     * @param wareSkuId 商品库存的ID
     * @return 商品库存的详细信息
     */
    WmsWareSkuVo getWareSkuInfoById(Long wareSkuId);

    /**
     * 批量删除商品库存信息
     *
     * @param wareSkuIdList 商品库存的ID列表
     */
    void batchDeleteWareSku(List<Long> wareSkuIdList);

    /**
     * 查询当前的Sku是否有库存
     *
     * @param skuId skuID
     * @return 是否有库存
     */
    Boolean hasStockBySkuId(Long skuId);

    /**
     * 锁库存
     *
     * @param responseVo 要锁库存的信息
     * @return 锁库存的结果
     */
    List<WmsLockStockResultVo> lockStock(OrderToResponseVo responseVo);

    /**
     * 释放库存
     *
     * @param stock  库存数
     * @param skuId  商品ID
     * @param wareId 仓库ID
     * @return 库存解锁结果
     */
    Boolean releaseLockStock(Integer stock, Long skuId, Long wareId);

    /**
     * @param detailList 要解锁库存的商品列表
     */
    void releaseStock(List<WmsWareOrderTaskDetail> detailList);
}