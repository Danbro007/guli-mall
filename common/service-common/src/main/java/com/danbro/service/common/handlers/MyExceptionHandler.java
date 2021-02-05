package com.danbro.service.common.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.danbro.common.entity.ResultBean;
import com.danbro.common.enums.ValidCode;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author liweimo
 * @Classname MyExceptionHandler
 * @Description TODO 全局异常处理器
 * @Date 2021/2/4 20:03
 */
@Slf4j
@ResponseBody
@RestControllerAdvice
public class MyExceptionHandler {

    /**
     * 负责捕获controller参数校验异常
     *
     * @param exception 校验异常
     * @return 带有校验失败的结果
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultBean<Map<String, String>> handleArgException(Exception exception) {
        MethodArgumentNotValidException e = (MethodArgumentNotValidException) exception;
        // 把校验失败的字段放入Map里，key为字段名，value为校验失败的提示。
        HashMap<String, String> errorMap = new HashMap<>(e.getBindingResult().getErrorCount());
        ResultBean<Map<String, String>> result = new ResultBean<>();
        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
        }
        result.setData(errorMap);
        result.setResultCode(ValidCode.VALID_PARAM_FAILURE);
        result.setSuccess(false);
        return result;
    }

    /**
     * 参数类型异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(InvalidFormatException.class)
    public ResultBean<List<String>> handleInvalidArgException(InvalidFormatException exception) {
        ResultBean<List<String>> result = new ResultBean<>();
        List<String> errorList = new ArrayList<>();
        exception.getPath().forEach(e -> errorList.add(e.getFieldName()));
        result.setData(errorList);
        result.setSuccess(false);
        result.setResultCode(ValidCode.VALID_FORMAT_FAILURE);
        return result;

    }
}
