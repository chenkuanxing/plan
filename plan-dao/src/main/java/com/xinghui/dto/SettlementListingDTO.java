package com.xinghui.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * <p>
 * 结算清单
 * </p>
 *
 * @author ckx
 * @since 2021-10-08
 */
@Data
public class SettlementListingDTO {

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

    @ApiModelProperty(value = "是否结算：0未结算 1已结算")
    private Long settlementLong;

    @ApiModelProperty(value = "是否结算")
    private String settlementName;

    @ApiModelProperty(value = "金额")
    private String money;

    @ApiModelProperty(value = "备注")
    private String remark;

}
