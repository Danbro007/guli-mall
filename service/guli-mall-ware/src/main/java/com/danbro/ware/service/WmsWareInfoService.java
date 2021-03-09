package com.danbro.ware.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.common.enums.PageParam;
import com.danbro.common.utils.Pagination;
import com.danbro.ware.controller.vo.FareVo;
import com.danbro.ware.controller.vo.WmsWareInfoVo;
import com.danbro.ware.entity.WmsWareInfo;

import java.util.List;


/**
 * 仓库信息(WmsWareInfo)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:06:16
 */
public interface WmsWareInfoService extends IService<WmsWareInfo> {
    /**
     * 分页查询仓库
     *
     * @param pageParam 分页条件
     * @param key       关键字
     * @return 分页查询结果
     */
    Pagination<WmsWareInfoVo, WmsWareInfo> queryPageByCondition(PageParam<WmsWareInfo> pageParam, String key);

    /**
     * 添加仓库
     *
     * @param wmsWareInfoVo 仓库信息
     * @return 添加完毕的仓库信息
     */
    WmsWareInfoVo insertWare(WmsWareInfoVo wmsWareInfoVo);

    /**
     * 修改仓库
     *
     * @param wmsWareInfoVo 仓库信息
     * @return 修改完毕的仓库信息
     */
    WmsWareInfoVo updateWare(WmsWareInfoVo wmsWareInfoVo);

    /**
     * 获取仓库的详细信息
     *
     * @param wareId 仓库Id
     * @return 仓库的详细信息
     */
    WmsWareInfoVo getWareInfoById(Long wareId);

    /**
     * 批量删除仓库
     *
     * @param wareIdList 要删除的仓库ID列表
     */
    void batchDeleteWare(List<Long> wareIdList);

    /**
     * 通过地址计算出运费
     *
     * @param addressId 地址ID
     * @return
     */
    FareVo calculateFare(Long addressId);
}