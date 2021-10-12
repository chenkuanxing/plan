package com.xinghui.enums;

import com.xinghui.vo.SelectVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author ckx
 */
public enum SettlementTypeEnum {

    /**
     * 未结算
     */
    NOT(0L, "未结算"),

    /**
     * 已结算
     */
    YES(1L, "已结算");

    private Long code;

    private String msg;

    SettlementTypeEnum(Long code, String msg) {
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

    public static String getDesc(Integer value) {
        if (value == null) {
            return null;
        }
        for (SettlementTypeEnum peopleTypeEnum : SettlementTypeEnum.values()) {
            if (Objects.equals(peopleTypeEnum.getCode(), value)) {
                return peopleTypeEnum.getMsg();
            }
        }
        return null;
    }

    public static List<SelectVO> getList() {
        List<SelectVO> list = new ArrayList<>();
        for (SettlementTypeEnum peopleTypeEnum : SettlementTypeEnum.values()) {
            SelectVO selectVO = new SelectVO();
            selectVO.setId(peopleTypeEnum.code);
            selectVO.setName(peopleTypeEnum.msg);
            list.add(selectVO);
        }
        return list;
    }
}
