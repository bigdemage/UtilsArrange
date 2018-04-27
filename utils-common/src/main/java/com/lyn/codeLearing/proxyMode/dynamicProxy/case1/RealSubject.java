package com.lyn.codeLearing.proxyMode.dynamicProxy.case1;

public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("From Real subject!");
    }
}
