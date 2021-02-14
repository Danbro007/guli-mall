package com.danbro.ware.service;


import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.common.enums.PageParam;
import com.danbro.common.utils.Pagination;
import com.danbro.ware.controller.vo.WmsPurchaseDetailVo;
import com.danbro.ware.entity.WmsPurchaseDetail;


/**
 * (WmsPurchaseDetail)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:06:16
 */
public interface WmsPurchaseDetailService extends IService<WmsPurchaseDetail> {
    /**
     * 分页查询采购单
     *
     * @param pageParam 分页条件
     * @param key       关键字
     * @param status    采购单状态
     * @param wareId    仓库ID
     * @return
     */
    Pagination<WmsPurchaseDetailVo, WmsPurchaseDetail> queryPageByCondition(PageParam<WmsPurchaseDetail> pageParam, String key, Integer status, Long wareId);

    /**
     * 获取采购单的详细信息
     *
     * @param purchaseDetailId 采购单ID
     * @return 采购单详细信息
     */
    WmsPurchaseDetailVo getPurchaseDetailInfoById(Long purchaseDetailId);

    /**
     * 添加采购单信息
     *
     * @param wmsPurchaseDetailVo 采购单信息
     * @return
     */
    WmsPurchaseDetailVo insertPurchaseDetail(WmsPurchaseDetailVo wmsPurchaseDetailVo);

    /**
     * 修改采购单信息
     *
     * @param wmsPurchaseDetailVo 采购单信息
     * @return
     */
    WmsPurchaseDetailVo updatePurchaseDetail(WmsPurchaseDetailVo wmsPurchaseDetailVo);

    /**
     * 批量删除采购单
     *
     * @param purchaseDetailList 采购单ID列表
     */
    void batchDeletePurchaseDetail(List<Long> purchaseDetailList);
}