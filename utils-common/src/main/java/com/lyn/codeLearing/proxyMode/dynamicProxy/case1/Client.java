package com.lyn.codeLearing.proxyMode.dynamicProxy.case1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Client {

    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();

        InvocationHandler handler = new DynamicSubject(realSubject);

        Class<?> clazz = handler.getClass();

        //一次性生成代理
        /**
         * @param   loader the class loader to define the proxy class
         * @param   interfaces th   e list of interfaces for the proxy class
         *          to implement
         * @param   h the invocation handler to dispatch method invocations to
         *
                    loader:一个ClassLoader对象，定义了由哪个ClassLoader对象来对生成的代理对象进行加载

                    interfaces:一个Interface对象的数组，表示的是我将要给我需要代理的对象提供一组什么接口，
                               如果我提供了一组接口给它，那么这个代理对象就宣称实现了该接口(多态)，这样我就能调用这组接口中的方法了

                    h:一个InvocationHandler对象，表示的是当我这个动态代理对象在调用方法的时候，会关联到哪一个InvocationHandler对象上
         */
        Subject subject = (Subject) Proxy.newProxyInstance
                (clazz.getClassLoader(),
                        realSubject.getClass().getInterfaces(),
                        handler);

        subject.request();

        System.out.println("-------------------------------------------");


    }
}
