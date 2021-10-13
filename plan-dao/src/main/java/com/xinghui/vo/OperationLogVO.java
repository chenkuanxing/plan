package com.xinghui.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 操作日志
 * </p>
 *
 * @author ckx
 * @since 2021-10-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "OperationLogVO对象", description = "操作日志")
public class OperationLogVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "操作时间")
    private LocalDateTime operationTime;

    @ApiModelProperty(value = "操作人id")
    private Long operationBy;

    @ApiModelProperty(value = "操作人姓名")
    private String operationName;

    @ApiModelProperty(value = "耗时（秒）")
    private Long elapsedTime;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "类型名称")
    private String typeName;

    @ApiModelProperty(value = "备注")
    private String remark;

}
