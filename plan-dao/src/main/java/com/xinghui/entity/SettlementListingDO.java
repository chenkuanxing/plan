package com.xinghui.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 结算清单
 * </p>
 *
 * @author ckx
 * @since 2021-10-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("settlement_listing")
@ApiModel(value = "SettlementListingDO对象", description = "结算清单")
public class SettlementListingDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "任务地址")
    @TableField("address")
    private String address;

    @ApiModelProperty(value = "完成日期")
    @TableField("plan_date")
    private LocalDate planDate;

    @ApiModelProperty(value = "获取人")
    @TableField("gain_by")
    private Long gainBy;

    @ApiModelProperty(value = "结算人")
    @TableField("settlement_by")
    private Long settlementBy;

    @ApiModelProperty(value = "是否结算：0未结算 1已结算")
    @TableField("is_settlement")
    private Boolean settlement;

    @ApiModelProperty(value = "金额")
    @TableField("money")
    private String money;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

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

    @ApiModelProperty(value = "逻辑删除标志：0未删除 1已删除")
    @TableField("is_deleted")
    private Boolean deleted;


}
