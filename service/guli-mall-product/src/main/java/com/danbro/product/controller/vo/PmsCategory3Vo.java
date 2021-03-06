package com.danbro.product.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Classname PmsCategory3Vo
 * @Description TODO
 * @Date 2021/2/17 14:48
 * @Created by Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class PmsCategory3Vo implements Serializable {
    private String id;
    private String catalog2Id;
    private String name;
}
