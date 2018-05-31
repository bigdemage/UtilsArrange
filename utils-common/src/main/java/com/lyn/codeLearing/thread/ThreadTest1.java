package com.lyn.codeLearing.thread;

public class ThreadTest1 {

    public static void main(String[] args) {

    Thread thread =new Thread(()->{
        System.out.println("aa");
    });

    thread.start();

    }
}
