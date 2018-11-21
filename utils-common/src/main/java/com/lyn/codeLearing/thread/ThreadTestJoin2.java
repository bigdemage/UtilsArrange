package com.lyn.codeLearing.thread;

import ch.qos.logback.core.util.TimeUtil;

import java.util.concurrent.TimeUnit;



public class ThreadTestJoin2 {

    private static String config;

    public static void main(String[] args) throws InterruptedException {


        Thread thread1 = new Thread(() -> {
            Thread thread2 = new Thread(() -> {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                config = "wyy";

            });

            thread2.start();
            try {
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        });
        thread1.start();
        thread1.join();

//        TimeUnit.SECONDS.sleep(2);

        System.out.println(thread1.isAlive());
        System.out.println(config);

    }

}
