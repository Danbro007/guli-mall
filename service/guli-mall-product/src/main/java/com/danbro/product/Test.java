package com.danbro.product;

import com.danbro.common.utils.MyBeanUtils;
import com.danbro.common.utils.MyReflectUtils;
import com.danbro.product.controller.vo.PmsBrandVo;
import com.danbro.product.entity.PmsBrand;

/**
 * @Classname Test
 * @Description TODO
 * @Date 2021/2/8 11:49
 * @Created by Administrator
 */
public class Test {
    public static void main(String[] args) {
        PmsBrand pmsBrand = new PmsBrand();
        pmsBrand.setBrandId(123L);
        PmsBrandVo pmsBrandVo = MyReflectUtils.getNewInstance(PmsBrandVo.class);
        MyBeanUtils.copyProperties(pmsBrand,pmsBrandVo);
        System.out.println(pmsBrandVo);
    }
}
