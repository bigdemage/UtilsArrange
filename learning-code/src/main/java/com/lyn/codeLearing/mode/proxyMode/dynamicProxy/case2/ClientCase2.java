package com.lyn.codeLearing.mode.proxyMode.dynamicProxy.case2;

import java.lang.reflect.Proxy;

public class ClientCase2 {

    public static void main(String[] args) {
        CommonInvocation handler = new CommonInvocation();

        Foo f = null;

        handler.setObj(new FooImpl());

        f= (Foo) Proxy.newProxyInstance(handler.getClass().getClassLoader(),
                new Class[]{Foo.class}, handler);

        f.doAction();

        //----------------------------
//
//        handler.setObj(new FooImpl2());
//        f= (Foo) Proxy.newProxyInstance(Foo.class.getClassLoader(),
//                new Class[]{Foo.class}, handler);
//        f.doAction();
    }

}
