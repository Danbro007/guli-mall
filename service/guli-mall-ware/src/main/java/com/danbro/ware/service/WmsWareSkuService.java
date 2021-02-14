package com.danbro.ware.service;


import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.common.enums.PageParam;
import com.danbro.common.utils.Pagination;
import com.danbro.ware.controller.vo.WmsWareSkuVo;
import com.danbro.ware.entity.WmsWareSku;


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
}