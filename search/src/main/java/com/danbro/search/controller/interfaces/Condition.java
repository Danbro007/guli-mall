package com.danbro.search.controller.interfaces;

import com.danbro.search.controller.vo.SearchParamVo;
import com.danbro.search.controller.vo.SearchResponseVo;

import java.util.List;

/**
 * @author Danrbo
 * @Classname NavVoInterface
 * @Description TODO 请求参数类型接口
 * @Date 2021/2/24 13:09
 */

public interface Condition {


    /**
     * 构建面包屑
     *
     * @param searchParamVo    搜索条件
     * @param requestUri       请求的uri
     * @param decodeQueryParam 请求路径的参数
     * @return 面包屑列表
     */
    List<SearchResponseVo.NavVo> buildNavVos(SearchParamVo searchParamVo, String requestUri, String decodeQueryParam);

}
