package com.danbro.product.controller.vo;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Classname PmsCategory2Vo
 * @Description TODO
 * @Date 2021/2/17 14:47
 * @Created by Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class PmsCategory2Vo {
    private String catalog1Id;
    private String id;
    private String name;
    private List<PmsCategory3Vo> catalog3List;
}
