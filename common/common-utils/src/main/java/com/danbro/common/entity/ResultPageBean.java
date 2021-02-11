package com.danbro.common.entity;

import com.danbro.common.enums.ResponseCode;
import com.danbro.common.interfaces.Result;
import com.danbro.common.interfaces.ResultCode;
import com.danbro.common.utils.Pagination;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Danrbo
 * @Classname ResultPageBean
 * @Description TODO 分页查询返回的对象
 * @Date 2021/2/5 22:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultPageBean<V,E> implements Result {
    private String msg;
    private Boolean success;
    private Integer code;
    private Pagination<V,E> page;

    @Override
    public void setResultCode(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMessage();
    }

    /**
     * 成功响应
     *
     * @param <V>  分页对象的Class
     * @return 分页结果
     */
    public static <V,E> ResultPageBean<V,E> ofSuccess() {
        ResultPageBean<V,E> resultPageBean = new ResultPageBean<>();
        resultPageBean.setSuccess(true);
        resultPageBean.setResultCode(ResponseCode.SUCCESS);
        return resultPageBean;
    }

    public static <V,E> ResultPageBean<V,E> ofSuccess(Pagination<V,E> pagination) {
        ResultPageBean<V,E> resultPageBean = ofSuccess();
        resultPageBean.setPage(pagination);
        return resultPageBean;
    }


    public static ResultPageBean<?,?> ofFailure(ResultCode resultCode) {
        ResultPageBean<?,?> resultPageBean = new ResultPageBean<>();
        resultPageBean.setSuccess(false);
        resultPageBean.setResultCode(resultCode);
        return resultPageBean;
    }
}
