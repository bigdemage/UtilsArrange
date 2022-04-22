package com.lyn.codeLearing.thread.countDownLatch;

import com.lyn.codeLearing.netWork.socketCase2.server.ServerOutputThread;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName JuGeLiZi
 * @Deacription TODO
 * @Author wrx
 * @Date 2022/3/18/018 17:34
 * @Version 1.0
 **/
public class JuGeLiZi {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(10);
        for(int i = 0; i < 10; i++) {
            new Thread(new Worker(startSignal, doneSignal)).start();
        }
        System.out.println("do something else 1");
        startSignal.countDown();
        System.out.println("do something else 2");
        doneSignal.await();
        System.out.println("wait for all to finsh");
    }

}

 class Worker implements Runnable{

    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;

    Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }

    @Override
    public void run() {
        try {
            startSignal.await();
            doWork();
            doneSignal.countDown();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    void doWork() {
        System.out.println("do work!");
    }
}

