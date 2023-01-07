package com.lyn.codeLearing.thread.countDownLatch;

import java.util.concurrent.CountDownLatch;


/**
 * 相当于线程计数器，待线程计数器清零时继续执行
 */
public class BasicCountDwnLatchTest {

    private static final int GROUP_SIZE=5;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch startCountDown =new CountDownLatch(1);

        CountDownLatch awaitCountDown=new CountDownLatch(GROUP_SIZE);

        for(int i=0;i<GROUP_SIZE;i++){
            new Thread(String.valueOf(i)){
                @Override
                public void run() {
                    try {
                        startCountDown.await();//等待主线程执行完毕，获得开始执行信号

                        System.out.println("处于等待的线程"+this.getName()+"开始自己的预期工作");

                        awaitCountDown.countDown();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }

        System.out.println("ready go");

        startCountDown.countDown();

        awaitCountDown.await();

        System.out.println("end");
    }

}
