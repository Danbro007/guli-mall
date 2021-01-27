package com.danbro.product.service.impl;
 
import com.danbro.product.entity.PmsCategory;
import com.danbro.product.mapper.PmsCategoryMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.product.service.PmsCategoryService;
import org.springframework.stereotype.Service;
 
/**
 * 商品三级分类(PmsCategory)表服务实现类
 *
 * @author makejava
 * @since 2021-01-27 21:30:43
 */
@Service
public class PmsCategoryServiceImpl extends ServiceImpl<PmsCategoryMapper, PmsCategory> implements PmsCategoryService {
    
}