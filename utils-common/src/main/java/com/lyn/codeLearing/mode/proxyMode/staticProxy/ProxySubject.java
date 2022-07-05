package com.lyn.codeLearing.mode.proxyMode.staticProxy;

public class ProxySubject extends Subject{
    //代理对象要有真实对象的引用
    private RealSubject realSubject;
    @Override
    public void request() {

        //在真实角色操作之前的附加操作
        this.preRequest();

        if(realSubject==null){
            realSubject=new RealSubject();
        }

        //真实角色所完成的事情；
        //这是静态代理，因为已经知道真实角色是谁
        realSubject.request();

        //在真实角色操作之后的附加操作
        this.postRequest();
    }


    private void preRequest(){
        System.out.println("pre request");
    }

    private void postRequest(){
        System.out.println("post request");
    }
}
