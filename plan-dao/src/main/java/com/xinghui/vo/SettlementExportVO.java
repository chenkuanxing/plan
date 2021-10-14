package com.xinghui.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
public class SettlementExportVO {

    @Excel(name = "获取人", width = 20, height = 30)
    private String gainByName;

    @Excel(name = "任务地址", width = 20, height = 30)
    private String address;

    @ApiModelProperty(value = "完成日期")
    @Excel(name = "完成日期", width = 20, height = 30)
    private LocalDate planDate;

    @Excel(name = "结算人", width = 20, height = 30)
    private String settlementByName;

    @Excel(name = "是否结算", width = 20, height = 30)
    private String settlementName;

    @Excel(name = "金额", width = 20, height = 30)
    private String money;

    @Excel(name = "备注", width = 20, height = 30)
    private String remark;

}
