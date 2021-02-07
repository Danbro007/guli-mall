package com.danbro.common.entity;

import com.danbro.common.enums.ResponseCode;
import com.danbro.common.interfaces.Result;
import com.danbro.common.interfaces.ResultCode;
import com.danbro.common.utils.PageUtils;
import lombok.Data;

/**
 * @author Danrbo
 * @Classname ResultPageBean
 * @Description TODO 分页查询返回的对象
 * @Date 2021/2/5 22:25
 */
@Data
public class ResultPageBean implements Result {
    private String message;
    private Boolean success;
    private Integer code;
    private PageUtils page;

    @Override
    public void setResultCode(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    /**
     * 成功响应
     *
     * @param page 分页数据
     * @param <T>  分页对象的Class
     * @return 分页结果
     */
    public static <T> ResultPageBean ofSuccess(PageUtils page) {
        ResultPageBean resultPageBean = new ResultPageBean();
        resultPageBean.setSuccess(true);
        resultPageBean.setResultCode(ResponseCode.SUCCESS);
        resultPageBean.setPage(page);
        return resultPageBean;
    }

    public static ResultPageBean ofFailure(ResultCode resultCode) {
        ResultPageBean resultPageBean = new ResultPageBean();
        resultPageBean.setSuccess(false);
        resultPageBean.setResultCode(resultCode);
        return resultPageBean;
    }
}
