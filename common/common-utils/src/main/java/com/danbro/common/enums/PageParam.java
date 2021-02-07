package com.danbro.common.enums;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Danrbo
 * @Classname PagingParam
 * @Description TODO 分页请求参数
 * @Date 2021/2/6 21:27
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PageParam<T> {
    /**
     * 当前页数
     */
    private Long page;
    /**
     * 每页显示的数据数量
     */
    private Long limit;
    /**
     * 排序字段
     */
    private String sidx;
    /**
     * 降序还是升序
     */
    private String order;
    /**
     * 分页数据对象
     */
    private Page<T> pageObject;


    public PageParam(Long page, Long limit, String sidx, String order) {
        this.page = page;
        this.limit = limit;
        this.sidx = sidx;
        this.order = order;
    }
}
