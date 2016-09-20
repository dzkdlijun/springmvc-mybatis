package com.leeo.springdata.inteceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by lijun on 2016/9/20.
 */
public class MyMethodInteceptor implements MethodInterceptor {

    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("before method invoke");
        Object object = invocation.proceed();
        System.out.println("after method invoke");
        return object;
    }
}
