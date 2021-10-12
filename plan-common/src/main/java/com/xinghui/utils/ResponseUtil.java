package com.xinghui.utils;

import com.xinghui.constants.ResultCode;

/**
 * @Author ckx
 * @since 2021/9/14
 * 返回信息处理类
 */
public class ResponseUtil {

    /**
     * 失败返回
     *
     * @param message
     * @return
     */
    public static ResultDTO fail(String message) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(ResultCode.FAIL.code());
        resultDTO.setMessage(message);
        return resultDTO;
    }

    /**
     * 自定义返回码
     *
     * @param code
     * @param message
     * @return
     */
    public static ResultDTO fail(Integer code, String message) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    /**
     * 成功返回
     *
     * @param data
     * @return
     */
    public static ResultDTO success(Object data) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(ResultCode.SUCCESS.code());
        resultDTO.setMessage(ResultCode.SUCCESS.message());
        resultDTO.setData(data);
        return resultDTO;
    }

    /**
     * 成功返回
     *
     * @return
     */
    public static ResultDTO success() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(ResultCode.SUCCESS.code());
        resultDTO.setMessage(ResultCode.SUCCESS.message());
        return resultDTO;
    }

}
