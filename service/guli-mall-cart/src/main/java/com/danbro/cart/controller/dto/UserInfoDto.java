package com.danbro.cart.controller.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @Classname UserInfoDto
 * @Description TODO
 * @Date 2021/3/3 20:37
 * @Created by Administrator
 */
@Data
public class UserInfoDto implements Serializable {
    private Long id;
    private String userKey;
    /**
     * 是否是临时用户（默认是 true）
     */
    private Boolean tempUser = true;
}
