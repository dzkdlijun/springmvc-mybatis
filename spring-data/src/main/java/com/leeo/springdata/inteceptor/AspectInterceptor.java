package com.leeo.springdata.inteceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by lijun on 2016/9/20.
 */
@Component
@Aspect
@Order(1)
public class AspectInterceptor {

    @Pointcut("@annotation(com.leeo.springdata.annotation.Intercept)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Aspect around before!!!");
        Object object = joinPoint.proceed();
        System.out.println("Aspect around after!!!");
        return object;
    }

}
