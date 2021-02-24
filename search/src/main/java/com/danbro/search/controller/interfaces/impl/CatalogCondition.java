package com.danbro.search.controller.interfaces.impl;

import cn.hutool.core.util.ReUtil;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.common.utils.MyObjectUtils;
import com.danbro.common.utils.MyStrUtils;
import com.danbro.search.controller.interfaces.NavVoInterface;
import com.danbro.search.controller.vo.PmsAttrDetailVo;
import com.danbro.search.controller.vo.PmsCategoryVo;
import com.danbro.search.controller.vo.SearchParamVo;
import com.danbro.search.controller.vo.SearchResponseVo;
import com.danbro.search.rpc.PmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Danrbo
 * @Classname CatalogCondition
 * @Description TODO
 * @Date 2021/2/24 14:08
 */
@Component
public class CatalogCondition implements NavVoInterface {
    private static final String CATALOG = "catalogId";
    /**
     * 请求参数的分隔符
     */
    private static final char REQUEST_PARAM_SPLIT_CHAR = '&';

    @Autowired
    PmsClient pmsClient;

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
            // attrs 不是第一个筛选条件
            // catalogId=225&skuPrice=1_900&attrs=1364072662106714113_绿色
            // 转换成 catalogId=225&skuPrice=1_900
            else {
                String format = String.format("&catalogId=%s", pmsCategoryVo.getCatId());
                newQueryParam = MyStrUtils.replace(decodeQueryParam, format, "");
            }
            String link = String.format("%s?%s", requestUri, newQueryParam);
            navVo.setNavName("分类").setNavValue(pmsCategoryVo.getName()).setLink(link);
            navVos.add(navVo);
            return navVos;
        }
        return null;
    }
}
