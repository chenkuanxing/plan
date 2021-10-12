package com.xinghui.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserInfo {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "中文名")
    private String nameCn;

    @ApiModelProperty(value = "英文名")
    private String nameEn;

    @ApiModelProperty(value = "工号")
    private String jobNumber;

    @ApiModelProperty(value = "性别：0未知；1男；2女")
    private Integer gender;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "电话")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    private String mailbox;

    @ApiModelProperty(value = "账户id")
    private Long accountId;

    @ApiModelProperty(value = "账户id")
    private String token;

    @ApiModelProperty(value = "终端：pc,mobile")
    private String terminal;

}
