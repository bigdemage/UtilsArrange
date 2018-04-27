package com.lyn.codeLearing.reflect;

import java.lang.reflect.Method;

public class DumpMethods {

    public static void main(String[] args) throws ClassNotFoundException {
        //静态方法获取对象
        Class<?> classType=Class.forName("java.lang.String");
//        Class<?> classType=Class.forName(args[0]);

        //反射出类的方法
        Method[] methods=classType.getDeclaredMethods();
        for (Method method:methods) {
            System.out.println(method);
        }


    }
}
