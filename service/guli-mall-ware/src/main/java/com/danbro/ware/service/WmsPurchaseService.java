package com.danbro.ware.service;
 
 
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.common.enums.PageParam;
import com.danbro.common.utils.Pagination;
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
     *
     * @param pageParam 分页参数
     * @param key 关键字
     * @param status 采购信息状态
     * @return 分页查询结果
     */
    Pagination<WmsPurchaseVo,WmsPurchase> queryPageByCondition(PageParam<WmsPurchase> pageParam, String key, Integer status);

    void batchDeletePurchase(List<Long> purchaseList);

    WmsPurchaseVo insertPurchase(WmsPurchaseVo wmsPurchaseVo);

    WmsPurchaseVo updatePurchase(WmsPurchaseVo wmsPurchaseVo);

    WmsPurchaseVo getPurchaseInfoById(Long purchaseId);

    Pagination<WmsPurchaseVo,WmsPurchase> queryPageUnreceiveByCondition(PageParam<WmsPurchase> pageParam);

    void mergePurchase(MergePurchaseVo mergePurchaseVo);

    void receivePurchase(List<Long> purchaseIdList);
}