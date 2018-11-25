package com.lyn.codeLearing.thread.interrupt;


import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class InterruptTest {

    private static final Logger LOGGER=Logger.getLogger(InterruptTest.class);

    public static void main(String[] args) throws InterruptedException {
        test2(new Object());

    }
    /**
     * 未阻塞状态的线程，interrupt只是把中断标志设置true，不会影响线程继续执行
     */
    static  void test1(){
        new Thread(() -> {
            Thread thread = Thread.currentThread();
            for (int i = 1; i < 8; i++) {
                if (i == 5) {
                    thread.interrupt();
                }
                LOGGER.info("线程中断标志:"+thread.isInterrupted());
            }
        }).start();
    }


    /**
     * interrupt中断已阻塞(sleep,wait,join)的线程,并且抛出异常
     */
    static void test2(Object obj) throws InterruptedException {
        Thread thread =new Thread(()->{
            Thread thread1 =Thread.currentThread();
           try{
               while(!thread1.isInterrupted()){
                   synchronized (obj) {
//                   TimeUnit.MILLISECONDS.sleep(20000);
                       obj.wait();
                   }
               }
           }catch (InterruptedException e){
               System.out.println("停止运行");
           }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
    }
}
