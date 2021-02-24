package com.danbro.search.controller.interfaces.impl;

import cn.hutool.core.util.ReUtil;
import com.danbro.common.utils.MyCollectionUtils;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.common.utils.MyStrUtils;
import com.danbro.search.controller.interfaces.NavVoInterface;
import com.danbro.search.controller.vo.PmsBrandVo;
import com.danbro.search.controller.vo.SearchParamVo;
import com.danbro.search.controller.vo.SearchResponseVo;
import com.danbro.search.rpc.PmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Danrbo
 * @Classname BrandCondition
 * @Description TODO
 * @Date 2021/2/24 14:19
 */
@Component
public class BrandCondition implements NavVoInterface {
    @Autowired
    PmsClient pmsClient;

    private static final String BRAND = "brandId";
    /**
     * 请求参数的分隔符
     */
    private static final char REQUEST_PARAM_SPLIT_CHAR = '&';

    @Override
    public List<SearchResponseVo.NavVo> buildNavVos(SearchParamVo searchParamVo, String requestUri, String decodeQueryParam) {
        if (MyCollectionUtils.isNotEmpty(searchParamVo.getBrandId())) {
            return searchParamVo.getBrandId().stream().map(brandId -> {
                SearchResponseVo.NavVo navVo = new SearchResponseVo.NavVo();
                PmsBrandVo pmsBrandVo = MyCurdUtils.rpcResultHandle(pmsClient.getBrandInfo(brandId), true);
                String newQueryParam;
                if (MyStrUtils.startWith(decodeQueryParam, BRAND)) {
                    if (MyStrUtils.contains(decodeQueryParam, REQUEST_PARAM_SPLIT_CHAR)) {
                        newQueryParam = ReUtil.replaceAll(decodeQueryParam, "^?brandId=.*?\\&", "");
                    } else {
                        newQueryParam = "";
                    }
                } else {
                    String format = String.format("&brandId=%s", pmsBrandVo.getBrandId());
                    newQueryParam = MyStrUtils.replace(decodeQueryParam, format, "");
                }
                String link = String.format("%s?%s", requestUri, newQueryParam);
                navVo.setNavName("品牌").setNavValue(pmsBrandVo.getName()).setLink(link);
                return navVo;
            }).collect(Collectors.toList());
        }
        return null;
    }
}
