package com.lyn.testCode;

public class ThreadLocalTest {

    public final static ThreadLocal<String> strThreadLocal =new ThreadLocal<String>();

    public static void main(String[] args) {
        strThreadLocal.set("3");
        System.out.println(strThreadLocal.get());
    }
}
