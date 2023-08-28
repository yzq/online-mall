package com.macro.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class UmsAdminParam {
    @NotEmpty
    @ApiModelProperty(value = "用户名", required = true)
    private String username;
    @ApiModelProperty(value = "用户昵称")
    private String nickname;
    @Email
    @ApiModelProperty(value = "邮箱")
    private String email;
    @NotEmpty
    @ApiModelProperty(value = "密码", required = true)
    private String password;
    @ApiModelProperty(value = "备注")
    private String note;
    @NotEmpty
    @ApiModelProperty(value = "启用状态", required = true)
    private boolean status;
    @ApiModelProperty(value = "头像")
    private String icon;
}
