package com.servlet.bean;


/**
 * 静态资源处理
 */
public class StaticResourceProcessor {

    public StaticResourceProcessor(){}

    public  void process(Request request,Response response){
        response.sendStaticResource();
    }
}
