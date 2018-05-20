package com.lyn.codeLearing.mode.proxyMode.staticProxy;

public class Client {

    public static void main(String[] args) {

        //使用代理
        Subject subject=new ProxySubject();

        subject.request();
    }
}
