package com.danbro.service.common.aop;

import com.danbro.common.entity.ResultBean;
import com.danbro.common.interfaces.Result;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Danrbo
 * @Classname ResultBeanControllerAop
 * @Description TODO
 * @Date 2021/2/5 11:12
 */
@Component
@Aspect
public class ResultBeanControllerAop extends ControllerAop {
    @Pointcut("execution(public com.danbro.common.entity.ResultBean *.*(..))")
    @Override
    protected void targetMethod() {

    }

    @Override
    protected Result<Map<String, String>> createResult() {
        return new ResultBean<>();
    }
}
