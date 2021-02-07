package com.danbro.common.entity;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.interfaces.Result;
import com.danbro.common.interfaces.ResultCode;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.common.utils.MyReflectUtils;
import com.danbro.common.utils.PageUtils;
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
public class ResultPageBean<V> implements Result {
    private String message;
    private Boolean success;
    private Integer code;
    private PageUtils<V> page;

    @Override
    public void setResultCode(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    /**
     * 成功响应
     *
     * @param <V>  分页对象的Class
     * @return 分页结果
     */
    public static <V> ResultPageBean<V> ofSuccess() {
        ResultPageBean<V> resultPageBean = new ResultPageBean<>();
        resultPageBean.setSuccess(true);
        resultPageBean.setResultCode(ResponseCode.SUCCESS);
        return resultPageBean;
    }

    public static <V> ResultPageBean<V> ofSuccess(PageUtils<V> pageUtils) {
        ResultPageBean<V> resultPageBean = ofSuccess();
        resultPageBean.setPage(pageUtils);
        return resultPageBean;
    }


    public static ResultPageBean<?> ofFailure(ResultCode resultCode) {
        ResultPageBean<?> resultPageBean = new ResultPageBean<>();
        resultPageBean.setSuccess(false);
        resultPageBean.setResultCode(resultCode);
        return resultPageBean;
    }
}
