package com.danbro.common.enums.pms;

/**
 * @Classname AttrType
 * @Description TODO 属性类型
 * @Date 2021/2/10 14:34
 * @Created by Administrator
 */
public enum AttrType {
    SALE("sale", 0),
    BASE("base", 1),
    ALL("all", 2);

    private String type;
    private Integer code;

    public String getType() {
        return this.type;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setType(Integer code) {
        this.code = code;
    }

    public void setType(String type) {
        this.type = type;
    }


    AttrType(String type, Integer code) {
        this.type = type;
        this.code = code;
    }
}
