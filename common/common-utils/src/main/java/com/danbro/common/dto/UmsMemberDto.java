package com.danbro.common.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Classname UmsMemberVo
 * @Description TODO
 * @Date 2021/2/11 11:48
 * @Created by Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class UmsMemberDto implements Serializable {

    private Long id;

    private Long levelId;

    private String username;

    private String password;
    private String nickname;

    private String mobile;

    private String email;

    private String header;

    private Integer gender;

    private Date birth;

    private String city;

    private String job;

    private String sign;

    private Integer sourceType;

    private Integer integration;

    private Integer growth;

    private Boolean status;

    private Date createTime;

    private String socialUid;

    private String accessToken;

    private Integer expiresIn;
}
