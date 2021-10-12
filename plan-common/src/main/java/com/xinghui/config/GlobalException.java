package com.xinghui.config;


import com.xinghui.constants.ResultCode;

/**
 * 全局异常处理
 */
public class GlobalException extends RuntimeException {

    private ResultCode resultCode;

    private Integer code;

    private String message;

    public GlobalException() {
    }

    public GlobalException(String msg) {
        super(msg);
        this.message = msg;
    }

    public GlobalException(Throwable cause) {
        super(cause);
    }

    public GlobalException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 自定义构造器 配合exceptionHandler自定义返回code
     */
    public GlobalException(ResultCode resultCode) {
        this.resultCode = resultCode;
        this.code = resultCode.code();
        this.message = resultCode.message();
    }

    public GlobalException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
