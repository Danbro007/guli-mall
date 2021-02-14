package com.danbro.ware.service;


import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.common.enums.PageParam;
import com.danbro.common.utils.Pagination;
import com.danbro.ware.controller.vo.DonePurchaseVo;
import com.danbro.ware.controller.vo.MergePurchaseVo;
import com.danbro.ware.controller.vo.WmsPurchaseVo;
import com.danbro.ware.entity.WmsPurchase;
import com.danbro.ware.entity.WmsPurchaseDetail;


/**
 * 采购信息(WmsPurchase)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:06:15
 */
public interface WmsPurchaseService extends IService<WmsPurchase> {
    /**
     * @param pageParam 分页参数
     * @param key       关键字
     * @param status    采购信息状态
     * @return 分页查询结果
     */
    Pagination<WmsPurchaseVo, WmsPurchase> queryPageByCondition(PageParam<WmsPurchase> pageParam, String key, Integer status);

    /**
     * 批量删除采购单
     *
     * @param purchaseList 采购单ID
     */
    void batchDeletePurchase(List<Long> purchaseList);

    /**
     * 添加采购单
     *
     * @param wmsPurchaseVo 采购单
     * @return 添加完毕的采购信息
     */
    WmsPurchaseVo insertPurchase(WmsPurchaseVo wmsPurchaseVo);

    /**
     * 修改采购单
     *
     * @param wmsPurchaseVo 采购单
     * @return 修改完毕的采购单
     */
    WmsPurchaseVo updatePurchase(WmsPurchaseVo wmsPurchaseVo);

    /**
     * 获取采购单
     *
     * @param purchaseId 采购单ID
     * @return 采购信息
     */
    WmsPurchaseVo getPurchaseInfoById(Long purchaseId);

    /**
     * 分页查询未领取的采购单
     *
     * @param pageParam 分页条件
     * @return 未领取的采购单列表
     */
    Pagination<WmsPurchaseVo, WmsPurchase> queryPageUnreceiveByCondition(PageParam<WmsPurchase> pageParam);

    /**
     * 合并采购单
     *
     * @param mergePurchaseVo 采购单ID和采购信息ID
     */
    void mergePurchase(MergePurchaseVo mergePurchaseVo);

    /**
     * 领取采购单
     *
     * @param purchaseIdList 采购单的ID列表
     */
    void receivePurchase(List<Long> purchaseIdList);

    /**
     * 完成采购
     *
     * @param donePurchaseVo 采购信息
     */
    void finishPurchase(DonePurchaseVo donePurchaseVo);
}