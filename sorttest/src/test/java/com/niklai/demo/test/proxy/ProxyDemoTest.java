package com.niklai.demo.test.proxy;

import net.sf.cglib.proxy.Enhancer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyDemoTest {

    @Test
    @DisplayName("Proxy代理模式")
    public void proxyTest() {
        DemoInterface demo = new DemoPrint();
        DemoInterface proxyDemo = (DemoInterface) Proxy.newProxyInstance(demo.getClass().getClassLoader(),
                demo.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("Proxy Before....");

                        // proxy是代理类的对象，当该对象方法被调用的时候，会触发InvocationHandler，而InvocationHandler里面又调用一次proxy里面的对象，
                        // 所以会不停地循环调用。并且，proxy对应的方法是没有实现的。所以是会循环的不停报错
                        Object result = method.invoke(demo, args);
                        System.out.println("Proxy After....");
                        return result;
                    }
                });

        proxyDemo.pring();
    }

    @Test
    @DisplayName("cglib代理模式")
    public void cglibTest() {
        DemoPrint demo = new DemoPrint();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(demo.getClass());
        enhancer.setCallback(new DemoCglib());
        DemoPrint demoPrint = (DemoPrint) enhancer.create();

        demoPrint.pring();
    }
}
