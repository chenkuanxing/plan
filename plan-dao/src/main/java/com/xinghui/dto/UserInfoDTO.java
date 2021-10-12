package com.xinghui.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author ckx
 * @since 2021-09-13
 */
@Data
public class UserInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "中文名")
    private String nameCn;

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

}
