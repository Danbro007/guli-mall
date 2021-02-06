package com.danbro.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.common.utils.PageUtils;
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
     * @param page  当前页
     * @param limit 每页显示的品牌数
     * @param key   关键字
     * @return 查询结果
     */
    PageUtils<PmsBrand> queryPage(Long page, Long limit, String key);

    /**
     * 添加或者修改品牌信息
     *
     * @param brand 品牌对象
     */
    PmsBrand insertOrUpdate(PmsBrand brand);

    /**
     * 获取品牌的详细信息
     *
     * @param brandId 品牌ID
     * @return 品牌对象
     */
    PmsBrand getBrandInfoById(Long brandId);
}