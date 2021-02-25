package com.danbro.search.utils;

import cn.hutool.core.util.URLUtil;
import com.danbro.common.utils.MyCollectionUtils;
import com.danbro.search.controller.interfaces.Condition;
import com.danbro.search.controller.vo.SearchParamVo;
import com.danbro.search.controller.vo.SearchResponseVo;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Danrbo
 * @Classname NavUtils
 * @Description TODO
 * @Date 2021/2/24 13:17
 */
public class NavUtils {
    public static List<SearchResponseVo.NavVo> buildNavList(SearchParamVo searchParamVo, HttpServletRequest request, List<Condition> conditions) {
        // 请求路径
        String requestUri = request.getRequestURI();
        // 请求路径参数
        String queryParam = request.getQueryString();
        // 请求参数解码
        String decodeQueryParam = URLUtil.decode(queryParam);
        ArrayList<SearchResponseVo.NavVo> navVos = new ArrayList<>();
        conditions.forEach(navVoInterface -> {
            List<SearchResponseVo.NavVo> voList = navVoInterface.buildNavVos(searchParamVo, requestUri, decodeQueryParam);
            if (MyCollectionUtils.isNotEmpty(voList)) {
                navVos.addAll(voList);
            }
        });
        return navVos;
    }
}

