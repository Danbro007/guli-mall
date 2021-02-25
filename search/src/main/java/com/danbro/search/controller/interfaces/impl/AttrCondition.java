package com.danbro.search.controller.interfaces.impl;

import cn.hutool.core.util.ReUtil;
import com.danbro.common.utils.MyCollectionUtils;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.common.utils.MyStrUtils;
import com.danbro.search.controller.interfaces.Condition;
import com.danbro.search.controller.vo.PmsAttrDetailVo;
import com.danbro.search.controller.vo.SearchParamVo;
import com.danbro.search.controller.vo.SearchResponseVo;
import com.danbro.search.rpc.PmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Danrbo
 * @Classname AttrCondition
 * @Description TODO 属性筛选条件
 * @Date 2021/2/24 13:10
 */
@Component
public class AttrCondition implements Condition {

    private static final String ATTRS = "attrs";
    private static final char SPLIT_CHAR = '_';
    /**
     * 请求参数的分隔符
     */
    private static final char REQUEST_PARAM_SPLIT_CHAR = '&';
    @Autowired
    PmsClient pmsClient;

    @Override
    public List<SearchResponseVo.NavVo> buildNavVos(SearchParamVo searchParamVo, String requestUri, String decodeQueryParam) {
        if (MyCollectionUtils.isNotEmpty(searchParamVo.getAttrs())) {
            return searchParamVo.getAttrs().stream().filter(attr -> MyStrUtils.split(attr, SPLIT_CHAR).size() == 2).map(attr -> {
                // 对属性和属性值进行字符串分割
                List<String> stringList = MyStrUtils.split(attr, SPLIT_CHAR);
                // 属性ID
                String attrId = stringList.get(0);
                // rpc 查询属性名
                PmsAttrDetailVo pmsAttr = MyCurdUtils.rpcResultHandle(pmsClient.getAttrInfo(Long.parseLong(attrId)));
                // 属性值
                String value = stringList.get(1);
                // 数据封装
                SearchResponseVo.NavVo navVo = new SearchResponseVo.NavVo();
                // attrs为第一个筛选条件
                String newQueryParam;
                if (MyStrUtils.startWith(decodeQueryParam, ATTRS)) {
                    // attrs=1364072662106714113_绿色&catalogId=225&skuPrice=1_900
                    // 替换成 ?catalogId=225&skuPrice=1_900
                    // attrs=1364072662106714113_绿色&catalogId=225&skuPrice=1_900 有一个以上的筛选条件并且是第一个筛选条件
                    if (MyStrUtils.contains(decodeQueryParam, REQUEST_PARAM_SPLIT_CHAR)) {
                        newQueryParam = ReUtil.replaceAll(decodeQueryParam, "^?attrs=.*?\\&", "");
                    }
                    // attrs=1364072662106714113_绿色 只有一个筛选条件
                    else {
                        newQueryParam = "";
                    }
                }
                // attrs 不是第一个筛选条件
                // catalogId=225&skuPrice=1_900&attrs=1364072662106714113_绿色
                // 转换成 catalogId=225&skuPrice=1_900
                else {
                    String format = String.format("&attrs=%s", attr);
                    newQueryParam = MyStrUtils.replace(decodeQueryParam, format, "");
                }
                String link = String.format("%s?%s", requestUri, newQueryParam);
                navVo.setNavName(pmsAttr.getAttrName()).setNavValue(value).setLink(link);
                return navVo;
            }).collect(Collectors.toList());
        }
        return null;
    }
}
