package com.danbro.order.controller.vo;

import java.util.List;
import javax.validation.constraints.NotNull;
import com.danbro.service.common.validtors.groups.Delete;
import lombok.Data;

/**
 * @Classname PmsAttrRelationList
 * @Description TODO
 * @Date 2021/2/10 17:00
 * @Created by Administrator
 */
@Data
public class PmsAttrRelationListVo {
    @NotNull(message = "要删除的属性与属性分组关系元素不能为空！",groups = Delete.class)
    private List<PmsAttrAttrgroupRelationVo> list;
}
