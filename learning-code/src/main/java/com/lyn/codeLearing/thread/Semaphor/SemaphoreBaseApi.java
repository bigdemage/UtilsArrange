package com.lyn.codeLearing.thread.Semaphor;


import com.lyn.codeLearing.thread.countDownLatch.CountDownBaseApiCase;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore许可证基本api
 */
public class SemaphoreBaseApi {

    public static void main(String[] args) throws InterruptedException {
        //初始化5个许可证、默认非公平模式
        Semaphore semaphore =new Semaphore(5);
        //初始化5个许可证并且是公平模式
        Semaphore semaphoreFair=new Semaphore(5,true);
        //是否公平
        System.out.println("semaphore公平？"+semaphore.isFair()+"  semaphoreFair公平？"+semaphoreFair.isFair());
//        baseApiCase(semaphore);//基本api方法

        Thread.sleep(10);
        new Thread(()->{
            //获取剩余的许可
            int permits =semaphore.drainPermits();
            System.out.println(Thread.currentThread().getName()+"获取了drain剩余的"+permits+"许可");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //释放所有许可
            semaphore.release(permits);
            System.out.println(Thread.currentThread().getName()+"释放了"+permits+"许可");
        }).start();
        Thread.sleep(10);
        new Thread(()->{
            //另启一个线程去获取
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //释放一个许可
            semaphore.release();
        }).start();
        Thread.sleep(10);
        System.out.println("当前的许可证数量"+semaphore.availablePermits());
        System.out.println("是否有等待释放的许可的线程"+semaphore.hasQueuedThreads());
        System.out.println("正在等待释放许可的线程的队列长度"+semaphore.getQueueLength());

    }

    private static void baseApiCase(Semaphore semaphore) throws InterruptedException {

        //获取许可证数量
        System.out.println("许可证数量："+semaphore.availablePermits());
        //获取一个许可证
        semaphore.acquire();
        System.out.println("获取一个许可证后的数量"+semaphore.availablePermits());
        //释放一个许可证
        semaphore.release();
        System.out.println("释放一个许可证后的数量"+semaphore.availablePermits());
        //获取3个许可证
        semaphore.acquire(3);
        System.out.println("获取三个许可证后的数量"+semaphore.availablePermits());
        //释放2个许可证
        semaphore.release(2);
        System.out.println("释放两个许可证后的数量"+semaphore.availablePermits());
        //获取剩余的所有许可证
        int count=semaphore.drainPermits();
        System.out.println("获取剩余的"+count+"许可证");
        semaphore.release(count);
        //是否有等待许可证的线程
        System.out.println("是否有等待许可证的线程？"+semaphore.hasQueuedThreads());
        //正在等待许可证的线程队列长度
        System.out.println("正在等待许可证的线程队列长度："+semaphore.getQueueLength());
    }


}
