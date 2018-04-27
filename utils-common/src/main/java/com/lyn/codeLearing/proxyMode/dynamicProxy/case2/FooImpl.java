package com.lyn.codeLearing.proxyMode.dynamicProxy.case2;

public class FooImpl implements Foo {
    @Override
    public void doAction() {
        System.out.println("FooImpl do!");
    }
}
