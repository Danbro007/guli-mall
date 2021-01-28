package com.danbro.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.product.entity.PmsCategory;
import com.danbro.product.mapper.PmsCategoryMapper;
import com.danbro.product.service.PmsCategoryService;
import org.springframework.stereotype.Service;

/**
 * 商品三级分类(PmsCategory)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
@Service
public class PmsCategoryServiceImpl extends ServiceImpl<PmsCategoryMapper, PmsCategory> implements PmsCategoryService {
    
}