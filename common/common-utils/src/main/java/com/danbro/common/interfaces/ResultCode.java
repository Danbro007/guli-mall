package com.danbro.common.interfaces;

/**
 * @author Danrbo
 * @Classname ResultCode
 * @Description TODO
 * @Date 2021/2/4 13:33
 */
public interface ResultCode {
    /**
     * 结果吗注释
     *
     * @return 注释
     */
    String getMessage();

    /**
     * 结果码代码
     *
     * @return 代码
     */
    Integer getCode();
}
