package com.lyn.codeLearing.reflect;

import java.lang.reflect.Method;

public class InvokeTester {

    public int add(int p1,int p2){
        return  p1+p2;
    }

    public String echo(String message){
        return "mdzz:"+message;
    }

    public static void main(String[] args) throws Exception {
        //java内置语法获取对象
        Class<?> classType=InvokeTester.class;
        //获取实例
        Object invokeTester=classType.newInstance();
        /*
        第一个参数：方法名
        第二个参数：方法参数类型
        */
        Method addMothod = classType.getMethod("add",new Class[]{int.class,int.class});
        Object result=addMothod.invoke(invokeTester,new Object[]{1,2});
        System.out.println(result);
        //-----------------------------------------------------
        Method echoMethod =classType.getMethod("echo",new Class[]{String.class});

        System.out.println(echoMethod.invoke(invokeTester,new Object[]{"lyn"}));;
    }
}
