package com.danbro.search.controller.vo;

import java.io.Serializable;
import java.util.List;

import com.danbro.search.controller.esModel.ProductSkuInfoEsModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Classname SearchResponseVo
 * @Description TODO 检索返回的数据
 * @Date 2021/2/20 16:24
 * @Created by Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class SearchResponseVo implements Serializable {
    /**
     * 查询结果
     */
    private List<ProductSkuInfoEsModel> products;
    /**
     * 当前页数
     */
    private Integer pageNum;

    /**
     * 总页数
     */
    private Integer totalPages;

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 导航页
     */
    private List<Integer> pageNavs;

    /**
     * 查询结果里涉及到的品牌
     */
    private List<BrandVo> brandVos;

    /**
     * 查询结果里涉及到的属性和属性值
     */
    private List<AttrVo> attrs;
    /**
     * 三级分类
     */
    private List<CatalogVo> catalogVos;

    /**
     * 面包屑导航栏
     */
    private List<NavVo> navs;

    @Data
    public static class BrandVo {
        private Long brandId;
        private String brandName;
        private String brandImg;
    }


    @Data
    public static class AttrVo {
        private Long attrId;
        private String attrName;
        private List<String> attrValues;
    }

    @Data
    public static class CatalogVo {
        private Long catalogId;
        private String catalogName;
    }

    @Data
    public static class NavVo {
        private String navName;
        private String navValue;
        private String link;
    }

}
