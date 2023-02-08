package com.jitgur.mall.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * 后台用户登录信息封装
 * Created by jitgur on 20230208
 */
@Setter
@Getter
public class UmsAdminLoginParam {

    @NotEmpty
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @NotEmpty
    @ApiModelProperty(value = "密码", required = true)
    private String password;

}
