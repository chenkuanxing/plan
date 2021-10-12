package com.xinghui.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 登入信息表
 * </p>
 *
 * @author ckx
 * @since 2021-09-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("account_info")
@ApiModel(value="AccountInfoDO对象", description="登入信息表")
public class AccountInfoDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "登录名")
    @TableField("account_name")
    private String accountName;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "1-启用，0-禁用")
    @TableField("is_enabled")
    private Boolean enabled;

    @ApiModelProperty(value = "最后一次登录时间")
    @TableField("gmt_last_login")
    private LocalDateTime gmtLastLogin;

    @ApiModelProperty(value = "创建者")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新者")
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "逻辑删除标志：0未删除1已删除")
    @TableField("is_deleted")
    private Boolean deleted;


}
