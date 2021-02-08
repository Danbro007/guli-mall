package com.danbro.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.common.enums.PageParam;
import com.danbro.common.utils.Pagination;
import com.danbro.product.controller.vo.PmsBrandVo;
import com.danbro.product.entity.PmsBrand;


/**
 * 品牌(PmsBrand)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
public interface PmsBrandService extends IService<PmsBrand> {
    /**
     * 分页查询品牌
     *
     * @param pageParam 分页参数
     * @param key       关键字
     * @return 查询结果
     */
    Pagination<PmsBrandVo,PmsBrand> queryPage(PageParam<PmsBrand> pageParam, String key);

    /**
     * 添加品牌信息
     *
     * @param brand 品牌对象
     */
    PmsBrand insert(PmsBrand brand);


    /**
     * 修改品牌信息
     *
     * @param brand 品牌对象
     */
    PmsBrand update(PmsBrand brand);

    /**
     * 获取品牌的详细信息
     *
     * @param brandId 品牌ID
     * @return 品牌对象
     */
    PmsBrand getBrandInfoById(Long brandId);

    /**
     * 批量删除品牌
     *
     * @param ids 品牌ID数组
     */
    void batchDeleteBrand(Long[] ids);
}