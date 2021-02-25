package com.danbro.product.controller.vo.front;

import java.util.List;
import com.danbro.product.controller.vo.PmsSkuImagesVo;
import com.danbro.product.controller.vo.PmsSkuInfoVo;
import com.danbro.product.controller.vo.PmsSpuInfoDescVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Danrbo
 * @Classname SkuItemVo
 * @Description TODO 商品详情Vo
 * @Date 2021/2/24 16:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkuItemVo {
    /**
     * sku商品详情
     */
    private PmsSkuInfoVo info;

    /**
     * sku的图片
     */
    private List<PmsSkuImagesVo> images;

    /**
     * spu 的介绍图
     */
    private PmsSpuInfoDescVo desc;

    /**
     * 属性分组
     */
    private List<SpuAttrGroupVo> groupAttrs;

    /**
     * sku的销售属性
     */
    private List<SkuSaleAttrValue> saleAttr;

    /**
     * 是否有库存
     */
    private Boolean hasStock;

    /**
     * 属性分组，每个属性分组包含多个基本属性。
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SpuAttrGroupVo {
        private String groupName;
        private List<SpuItemBaseAttr> spuItemBaseAttrs;
    }


    /**
     * 基本属性对象
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SpuItemBaseAttr {
        private String attrName;
        private String attrValue;
    }

    /**
     * sku的销售属性
     */
    @Data
    public static class SkuSaleAttrValue {
        private Long attrId;
        private String attrName;
        private String attrValues;

    }
}
