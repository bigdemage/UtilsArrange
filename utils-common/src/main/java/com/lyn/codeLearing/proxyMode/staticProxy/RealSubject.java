package com.lyn.codeLearing.proxyMode.staticProxy;


public class RealSubject extends  Subject{
    @Override
    public void request() {
        System.out.println("From real subject.");
    }
}
