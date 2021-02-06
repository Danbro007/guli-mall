package com.danbro.common.enums.pms;

/**
 * @author Danrbo
 * @Classname OrderType
 * @Description TODO 排序类型
 * @Date 2021/2/5 22:51
 */
public enum OrderType {
    /**
     * 升序还是降序
     */
    DESC("desc"),
    ASC("asc");

    private String type;

    OrderType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
