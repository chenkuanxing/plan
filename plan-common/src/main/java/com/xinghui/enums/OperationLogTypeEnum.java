package com.xinghui.enums;

import com.xinghui.vo.SelectVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author ckx
 */
public enum OperationLogTypeEnum {

    /**
     * 操作类型
     */
    LOGIN(0L, "登入"),
    USER(1L, "用户管理"),
    PLAN(2L, "任务清单"),
    ROLE(3L, "权限管理"),
    SYS_INFO(4L, "系统信息");

    private Long code;

    private String msg;

    OperationLogTypeEnum(Long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static String getDesc(Long value) {
        if (value == null) {
            return null;
        }
        for (OperationLogTypeEnum peopleTypeEnum : OperationLogTypeEnum.values()) {
            if (Objects.equals(peopleTypeEnum.getCode(), value)) {
                return peopleTypeEnum.getMsg();
            }
        }
        return null;
    }

    public static List<SelectVO> getList() {
        List<SelectVO> list = new ArrayList<>();
        for (OperationLogTypeEnum peopleTypeEnum : OperationLogTypeEnum.values()) {
            SelectVO selectVO = new SelectVO();
            selectVO.setId(peopleTypeEnum.code);
            selectVO.setName(peopleTypeEnum.msg);
            list.add(selectVO);
        }
        return list;
    }
}
