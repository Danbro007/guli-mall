package com.danbro.member.controller.vo;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.danbro.common.interfaces.Converter;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.member.entity.UmsMember;
import com.danbro.service.common.validtors.anotations.IsBool;
import com.danbro.service.common.validtors.anotations.IsBirthday;
import com.danbro.service.common.validtors.anotations.IsMobile;
import com.danbro.service.common.validtors.groups.Insert;
import com.danbro.service.common.validtors.groups.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.URL;

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
public class UmsMemberVo implements Serializable, Converter<UmsMember, UmsMemberVo> {

    @NotNull(message = "修改会员时ID不能为空！", groups = Insert.class)
    @Null(message = "添加会员时ID必须为空！", groups = Update.class)
    @ApiModelProperty("id")
    private Long id;

    @NotNull(message = "添加会员时会员等级ID不能为空！", groups = Update.class)
    @ApiModelProperty("会员等级id")
    private Long levelId;

    @NotBlank(message = "添加会员时用户名不能为空！", groups = Insert.class)
    @ApiModelProperty("用户名")
    private String username;

    @NotBlank(message = "添加会员时密码不能为空！", groups = Insert.class)
    @ApiModelProperty("密码")
    private String password;

    @NotBlank(message = "添加会员时昵称不能为空！", groups = Insert.class)
    @ApiModelProperty("昵称")
    private String nickname;

    @NotBlank(message = "添加会员时手机号不能为空！")
    @IsMobile(groups = {Insert.class, Update.class})
    @ApiModelProperty("手机号码")
    private String mobile;

    @NotBlank(message = "添加时邮箱不能为空！", groups = {Insert.class})
    @Email(message = "不符合邮箱的格式！", groups = {Insert.class, Update.class})
    @ApiModelProperty("邮箱")
    private String email;

    @NotBlank(message = "头像的地址不能为空！", groups = Insert.class)
    @URL(message = "头像的地址必须符合URL格式！", groups = {Insert.class, Update.class})
    @ApiModelProperty("头像")
    private String header;

    @NotNull(message = "添加会员时性别必须存在！", groups = Insert.class)
    @IsBool(message = "true表示男，false表示女！", groups = {Insert.class, Update.class})
    @ApiModelProperty("性别")
    private Integer gender;

    @NotBlank(message = "添加会员时生日不能为空！", groups = {Insert.class, Update.class})
    @IsBirthday(groups = {Insert.class, Update.class})
    @ApiModelProperty("生日")
    private Date birth;

    @NotBlank(message = "添加会员时城市不能为空！", groups = Insert.class)
    @ApiModelProperty("所在城市")
    private String city;

    @NotBlank(message = "添加会员时职业不能为空", groups = {Insert.class})
    @ApiModelProperty("职业")
    private String job;

    @ApiModelProperty("个性签名")
    private String sign;

    @ApiModelProperty("用户来源")
    private Integer sourceType;

    @ApiModelProperty("积分")
    private Integer integration;

    @ApiModelProperty("成长值")
    private Integer growth;

    @ApiModelProperty("启用状态")
    private Boolean status;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("注册时间")
    private Date createTime;

    @Override
    public UmsMemberVo convertToVo(UmsMember umsMember) {
        MyBeanUtils.copyProperties(umsMember, this);
        return this;
    }

    @Override
    public UmsMember convertToEntity() {
        UmsMember umsMember = new UmsMember();
        MyBeanUtils.copyProperties(this, umsMember);
        return umsMember;
    }
}
