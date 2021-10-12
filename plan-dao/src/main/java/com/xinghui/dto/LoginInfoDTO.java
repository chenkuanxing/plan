package com.xinghui.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginInfoDTO {

    @ApiModelProperty(value = "账号")
    private String accountName;

    @ApiModelProperty(value = "密码, MD5后上传")
    private String password;

}
