package com.danbro.service.handlers;

import java.util.HashMap;
import java.util.Map;
import com.danbro.service.base.entity.ResultBean;
import com.danbro.service.base.enums.ResponseCode;
import com.danbro.service.base.enums.ValidCode;
import com.danbro.service.exceptions.GuliMallException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Classname MyExceptionHandler
 * @Description TODO
 * @Date 2021/2/4 20:03
 * @Created by Administrator
 */
@Slf4j
@ResponseBody
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultBean<Map<String, String>> handleArgException(Exception exception) {
        MethodArgumentNotValidException e = (MethodArgumentNotValidException) exception;
        HashMap<String, String> errorMap = new HashMap<>(16);
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

    @ExceptionHandler(GuliMallException.class)
    public ResultBean<?> handleGuliMallException(Exception exception) {
        GuliMallException e = (GuliMallException) exception;
        ResultBean<Map<String, String>> result = new ResultBean<>();
        result.setSuccess(false);
        result.setMessage(e.getMessage());
        result.setCode(e.getCode());
        return result;
    }

    @ExceptionHandler(Exception.class)
    public ResultBean<Map<String, String>> handleUnKnownException(Exception exception) {
        log.error("{} error {}", exception.getCause(), exception.getLocalizedMessage());
        ResultBean<Map<String, String>> result = new ResultBean<>();
        result.setSuccess(false);
        result.setMessage(ResponseCode.UNKNOWN_EXCEPTION.getMessage());
        result.setCode(ResponseCode.UNKNOWN_EXCEPTION.getCode());
        return result;
    }
}
