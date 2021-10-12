package com.xinghui.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdatePassWordDTO {

    @ApiModelProperty(value = "新账号")
    private String newPassword;

    @ApiModelProperty(value = "确认密码")
    private String confirmNewPassword;

}
