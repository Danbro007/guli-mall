package com.danbro.product.aop;

import com.danbro.service.base.enums.ResponseCode;
import com.danbro.service.base.enums.ValidCode;
import com.danbro.service.base.interfaces.Result;
import com.danbro.service.exceptions.GuliMallException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Danrbo
 * @Classname ControllerAop
 * @Description TODO
 * @Date 2021/2/4 12:27
 */
@Slf4j
public abstract class ControllerAop {

    protected abstract void targetMethod();

    @Around("targetMethod()")
    public Object handleControllerMethod(ProceedingJoinPoint point) {
        long startTime = System.currentTimeMillis();
        try {
            Object result = point.proceed();
            long elapsedTime = System.currentTimeMillis() - startTime;
            // 记录操作花费的时间
            log.info("【{}】 use time:{}", point.getSignature(), elapsedTime);
            return result;
        } catch (Throwable e) {
            return handleException(point, e);
        }
    }

    /**
     * 负责处理异常
     *
     * @param point aop的切点
     * @param e     异常
     * @return 异常结果
     */
    private Result<?> handleException(ProceedingJoinPoint point, Throwable e) {
        Result<Map<String, String>> result = this.createResult();
        // 参数校验异常
        if (e instanceof MethodArgumentNotValidException) {
            HashMap<String, String> errorMap = new HashMap<>(16);
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            if (bindingResult.hasErrors()) {
                bindingResult.getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
            }
            result.setData(errorMap);
            result.setResultCode(ValidCode.VALID_PARAM_FAILURE);
            result.setSuccess(false);
            return result;
        }
        // 普通异常
        else if (e instanceof GuliMallException) {
            GuliMallException guliMallException = (GuliMallException) e;
            result.setSuccess(false);
            result.setMessage(guliMallException.getMessage());
            result.setCode(guliMallException.getCode());
            return result;
        }
        // 未知异常
        else {
            //Todo 发送邮件
            log.error("{} error {}", point.getSignature(), e);
            result.setMessage(e.toString());
            result.setCode(ResponseCode.UNKNOWN_EXCEPTION.getCode());
            return result;
        }
    }

    /**
     * 创建结果对象
     *
     * @return 结果对象
     */
    protected abstract <T> Result<T> createResult();


}
