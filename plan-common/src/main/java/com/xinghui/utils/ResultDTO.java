package com.xinghui.utils;

import lombok.Data;

/**
 * @author ckx
 * @since 2021/9/14
 *
 */

@Data
public class ResultDTO {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

}
