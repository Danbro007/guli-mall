package com.danbro.search.controller.interfaces;

import com.danbro.search.controller.vo.SearchParamVo;
import com.danbro.search.controller.vo.SearchResponseVo;

import java.util.List;

/**
 * @author Danrbo
 * @Classname NavVoInterface
 * @Description TODO
 * @Date 2021/2/24 13:09
 */

public interface NavVoInterface {

    List<SearchResponseVo.NavVo> buildNavVos(SearchParamVo searchParamVo, String requestUri, String decodeQueryParam);

}
