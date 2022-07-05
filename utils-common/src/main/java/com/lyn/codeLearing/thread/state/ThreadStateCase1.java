package com.lyn.codeLearing.thread.state;


import java.sql.Time;
import java.util.concurrent.TimeUnit;

/*
   NEW->RUNNABLE->WAITING->RUNNABLE->TERMINATED
 */
public class ThreadStateCase1 {

    private static Object obj =new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread  thread =new Thread(()->{
            synchronized (obj){
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("o ho:::" +Thread.currentThread().getState());
        });

        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        TimeUnit.MILLISECONDS.sleep(150);
        System.out.println(thread.getState());

//        new Thread(()->{
//            synchronized (obj){
//                obj.notify();
//            }
//        }).start();
        relock();
        TimeUnit.MILLISECONDS.sleep(10);

        System.out.println(thread.getState());
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println(thread.getState());

    }

    private static void relock() {
        synchronized (obj){
            obj.notify();
        }
    }


}
