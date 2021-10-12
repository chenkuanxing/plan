package com.xinghui.enums;

import java.util.Objects;

/**
 * @author ckx
 */
public enum TerminalTypeEnum {

    /**
     * 电脑端
     */
    PC("pc", "电脑端"),

    /**
     * 手机端
     */
    MOBILE("mobile", "手机端");

    private String code;

    private String msg;

    TerminalTypeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static String getDesc(Integer value) {
        if (value == null) {
            return null;
        }
        for (TerminalTypeEnum peopleTypeEnum : TerminalTypeEnum.values()) {
            if (Objects.equals(peopleTypeEnum.getCode(), value)) {
                return peopleTypeEnum.getMsg();
            }
        }
        return null;
    }
}
