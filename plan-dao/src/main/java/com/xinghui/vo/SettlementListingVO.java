package com.xinghui.vo;

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
@ApiModel(value = "SettlementListingVO对象", description = "结算清单")
public class SettlementListingVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "任务地址")
    private String address;

    @ApiModelProperty(value = "完成日期")
    private LocalDate planDate;

    @ApiModelProperty(value = "获取人")
    private Long gainBy;

    @ApiModelProperty(value = "获取人名称")
    private String gainByName;

    @ApiModelProperty(value = "结算人")
    private Long settlementBy;

    @ApiModelProperty(value = "结算人名称")
    private String settlementByName;

    @ApiModelProperty(value = "是否结算：0未结算 1已结算")
    private Boolean settlement;

    @ApiModelProperty(value = "是否结算")
    private String settlementName;

    @ApiModelProperty(value = "金额")
    private String money;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建者")
    private Long createBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新者")
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "逻辑删除标志：0未删除 1已删除")
    private Boolean deleted;


}
