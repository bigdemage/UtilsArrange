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
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj){
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        TimeUnit.SECONDS.sleep(3);
        System.out.println(thread.getState());
        reLock();
        System.out.println(thread.getState());
        TimeUnit.SECONDS.sleep(2);
        System.out.println(thread.getState());

    }
    static void reLock(){
        synchronized (obj){
            obj.notify();
        }
    }

}
