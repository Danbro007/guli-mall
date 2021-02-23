package com.danbro.search.controller.vo;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Classname SearchParamVo
 * @Description TODO
 * @Date 2021/2/20 16:14
 * @Created by Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class SearchParamVo{
    /**
     * 关键字
     */
    private String keyword;

    /**
     * 三级分类的ID
     */
    private Long catalogId;

    /**
     * 品牌ID（支持多选）
     */
    private List<Long> brandId;

    /**
     * 排序规则:
     * saleCount_desc/asc
     * skuPrice_desc/asc
     * hotScore_desc/asc
     */
    private String sort;

    /**
     * 有没有库存
     */
    private Boolean hasStock;

    /**
     * 价格区间：
     * 1_500 表示1~500
     * 1_ 表示 大于等于 1
     * _500 表示小于等于 500
     */
    private String skuPrice;

    /**
     * 属性
     * attrs=2_5寸：6寸 表示 属性ID为 2 的属性值为 5 寸和 6 寸
     */
    private List<String> attrs;

    /**
     * 页码
     */
    private Integer pageNum;
}
