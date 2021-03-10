package com.danbro.ware.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.danbro.ware.entity.WmsWareSku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品库存(WmsWareSku)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-28 19:06:16
 */
@Mapper
public interface WmsWareSkuMapper extends BaseMapper<WmsWareSku> {

    /**
     * 返回还有库存的仓库ID
     *
     * @param skuId skuId
     * @return 仓库Id列表
     */
    List<Long> getWareIdListHasStock(Long skuId);

    /**
     * 锁指定仓库的指定商品的库存
     *
     * @param skuId  商品skuId
     * @param wareId 仓库ID
     * @param stock  要锁的库存数
     * @return 结果
     */
    Long lockStockBySkuId(@Param("skuId") Long skuId, @Param("wareId") Long wareId, @Param("stock") Integer stock);
}