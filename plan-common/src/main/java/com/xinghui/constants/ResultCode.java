package com.xinghui.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * 返回值
 */
public enum ResultCode {

    /* 成功状态码 */
    SUCCESS(200, "成功"),

    /* 失败状态码 */
    FAIL(-200, "失败"),

    /* 登入错误：201 */
    EXCEPTION_TOKEN(201, "异常token"),
    OVERDUE_TOKEN(202, "过期token"),
    PASS_ERRER(203, "密码错误"),
    USER_NOT_EXIST(204, "用户不存在,请重新登录"),
    NOT_TOKEN(205, "无token，请重新登录"),
    INVALID_TOKEN(206, "无效tokan"),

    GAINBY_NOT_NULL(300001, "获取人不能为空！"),
    SETTLEMENTBY_NOT_NULL(300002, "结算人不能为空！"),
    ADDRESS_NOT_NULL(300003, "任务地址不能为空！"),
    PLANDATE_NOT_NULL(300004, "任务时间不能为空！"),
    MONEY_NOT_NULL(300005, "金额不能为空！"),
    SETTLEMENT_NOT_NULL(300006, "是否结算不能为空！"),
    NEW_PASSWORD_NOT_NULL(300007, "新密码不能为空！"),
    CONFIRM_NEW_PASSWORD_NOT_NULL(300008, "确认新密码不能为空！"),
    NOT_CONFIRM_NEW_PASSWORD(300009, "确认新密码不正确！");

    private Integer code;

    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessage(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.message;
            }
        }
        return name;
    }

    public static Integer getCode(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.code;
            }
        }
        return null;
    }

    //校验重复的code值
    public static void main(String[] args) {
        ResultCode[] ApiResultCodes = ResultCode.values();
        List<Integer> codeList = new ArrayList<Integer>();
        for (ResultCode ApiResultCode : ApiResultCodes) {
            if (codeList.contains(ApiResultCode.code)) {
                System.out.println(ApiResultCode.code);
            } else {
                codeList.add(ApiResultCode.code());
            }
        }
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    @Override
    public String toString() {
        return this.name();
    }

}
