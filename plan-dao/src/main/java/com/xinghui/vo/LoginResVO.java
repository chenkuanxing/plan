package com.xinghui.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginResVO {

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "用户中文名")
    private String nameCn;

    @ApiModelProperty(value = "用户英文名")
    private String nameEn;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "性别")
    private Integer gender;

    @ApiModelProperty(value = "token")
    private String accessToken;

}
