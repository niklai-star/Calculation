package com.niklai.demo.test.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class DemoCglib implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("Cglib Before....");
        Object result = methodProxy.invokeSuper(o, args);
        System.out.println("Cglib Before....");
        return result;
    }
}
