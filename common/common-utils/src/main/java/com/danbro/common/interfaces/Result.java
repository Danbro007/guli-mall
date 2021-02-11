package com.danbro.common.interfaces;

/**
 * @author Danrbo
 * @Classname Result
 * @Description TODO
 * @Date 2021/2/4 13:12
 */
public interface Result {
    /**
     * 是否成功
     *
     * @param bool 响应结果
     */
    void setSuccess(Boolean bool);

    /**
     * 获取成功结果
     *
     * @return 结果
     */
    Boolean getSuccess();

    /**
     * 设置状态码对象
     *
     * @param resultCode 状态码对象
     */
    void setResultCode(ResultCode resultCode);

    /**
     * 设置返回的消息
     *
     * @param msg 消息内容
     */
    void setMsg(String msg);

    /**
     * 返回消息内容
     *
     * @return 消息内容
     */
    String getMsg();

    /**
     * 设置状态码
     *
     * @param code 状态码
     */
    void setCode(Integer code);

    /**
     * 获取状态码
     *
     * @return 响应状态码
     */
    Integer getCode();
}
