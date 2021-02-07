package com.danbro.service.common.aop;

import com.danbro.common.enums.ResponseCode;
import com.danbro.common.interfaces.Result;
import com.danbro.common.exceptions.GuliMallException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

/**
 * @author Danrbo
 * @Classname ControllerAop
 * @Description TODO
 * @Date 2021/2/5 10:55
 */
@Slf4j
public abstract class ControllerAop {

    protected abstract void targetMethod();


    @Around("targetMethod()")
    public Object process(ProceedingJoinPoint joinPoint) {
        long start = System.currentTimeMillis();
        try {
            // 校验失败异常会先走异常处理器再走这里
            Object result = joinPoint.proceed();
            long expandTime = System.currentTimeMillis() - start;
            log.info(String.format("【%s】----->use time:%s", joinPoint.getSignature(), expandTime));
            return result;
        } catch (Throwable throwable) {
            return handleException(joinPoint, throwable);
        }
    }

    /**
     * 负责处理异常
     *
     * @param joinPoint 切面
     * @param e         异常
     * @return 失败结果
     */
    protected Result handleException(ProceedingJoinPoint joinPoint, Throwable e) {
        Result result = this.createResult();
        // 已知的普通异常
        if (e instanceof GuliMallException) {
            GuliMallException guliMallException = (GuliMallException) e;
            result.setCode(guliMallException.getCode());
            result.setMessage(guliMallException.getMessage());
            result.setSuccess(false);
        }
        // 未知错误
        else {
            log.error("{} error {}", joinPoint.getSignature(), e);
            // TODO 未知的异常，应该格外注意，可以发送邮件通知等
            result.setMessage(e.toString());
            result.setCode(ResponseCode.UNKNOWN_EXCEPTION.getCode());
        }
        return result;
    }


    protected abstract Result createResult();
}
