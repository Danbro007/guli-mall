package com.danbro.product.aop;

import com.danbro.service.base.entity.ResultBean;
import com.danbro.service.base.interfaces.Result;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Danrbo
 * @Classname ResultBeanControllerAop
 * @Description TODO
 * @Date 2021/2/4 14:25
 */
@Aspect
@Component
public class ResultBeanControllerAop extends ControllerAop {
    @Override
    @Pointcut("execution(public com.danbro.service.base.entity.ResultBean com.danbro.product.*(..))")
    protected void targetMethod() {

    }

    @Override
    protected <T> Result<T> createResult() {
        return new ResultBean<>();
    }
}
