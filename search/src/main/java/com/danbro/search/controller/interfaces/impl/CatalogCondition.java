package com.danbro.search.controller.interfaces.impl;

import cn.hutool.core.util.ReUtil;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.common.utils.MyObjectUtils;
import com.danbro.common.utils.MyStrUtils;
import com.danbro.search.controller.interfaces.Condition;
import com.danbro.search.controller.vo.PmsCategoryVo;
import com.danbro.search.controller.vo.SearchParamVo;
import com.danbro.search.controller.vo.SearchResponseVo;
import com.danbro.search.rpc.PmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Danrbo
 * @Classname CatalogCondition
 * @Description TODO 三级分类筛选条件
 * @Date 2021/2/24 14:08
 */
@Component
public class CatalogCondition implements Condition {

    @Autowired
    PmsClient pmsClient;

    private static final String CATALOG = "catalogId";

    private static final String NAV_NAME = "分类";

    /**
     * 请求参数的分隔符
     */
    private static final char REQUEST_PARAM_SPLIT_CHAR = '&';


    @Override
    public List<SearchResponseVo.NavVo> buildNavVos(SearchParamVo searchParamVo, String requestUri, String decodeQueryParam) {
        if (MyObjectUtils.isNotNull(searchParamVo.getCatalogId()) && searchParamVo.getCatalogId() > 0) {
            List<SearchResponseVo.NavVo> navVos = new ArrayList<>();
            PmsCategoryVo pmsCategoryVo = MyCurdUtils.rpcResultHandle(pmsClient.getCategoryInfo(searchParamVo.getCatalogId()), true);
            SearchResponseVo.NavVo navVo = new SearchResponseVo.NavVo();
            String newQueryParam;
            if (MyStrUtils.startWith(decodeQueryParam, CATALOG)) {
                if (MyStrUtils.contains(decodeQueryParam, REQUEST_PARAM_SPLIT_CHAR)) {
                    newQueryParam = ReUtil.replaceAll(decodeQueryParam, "^?catalogId=.*?\\&", "");
                } else {
                    newQueryParam = "";
                }
            }
            else {
                String format = String.format("&catalogId=%s", pmsCategoryVo.getCatId());
                newQueryParam = MyStrUtils.replace(decodeQueryParam, format, "");
            }
            String link = String.format("%s?%s", requestUri, newQueryParam);
            navVo.setNavName(NAV_NAME).setNavValue(pmsCategoryVo.getName()).setLink(link);
            navVos.add(navVo);
            return navVos;
        }
        return null;
    }
}
