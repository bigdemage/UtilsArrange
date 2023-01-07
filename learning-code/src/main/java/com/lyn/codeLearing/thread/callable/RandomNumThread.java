package com.lyn.codeLearing.thread.callable;


import org.apache.commons.lang3.RandomUtils;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 分别以下面三种方式实现随机数的获取：
    1.Runnable + Thread
    2.Runnable + Executor
    3.Callable + Future + Executor
 */
public class RandomNumThread {

    private static  AtomicInteger atomicInteger =new AtomicInteger(0);


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Thread(new RandomIntRunable()).start();
        while(atomicInteger.get()==0);
        System.out.println("Runnable + Thread::"+atomicInteger.get());


        atomicInteger=new AtomicInteger(0);
        ExecutorService executor= Executors.newSingleThreadExecutor();
        executor.execute(new RandomIntRunable());
        while(atomicInteger.get()==0);
        System.out.println("Runnable + Executor::"+atomicInteger.get());
        executor.shutdown();

        ExecutorService executor2 =Executors.newSingleThreadExecutor();
        Future future=executor2.submit(new RandomIntCallable());
        System.out.println("Callable + Future + Executor::"+future.get());
        executor2.shutdown();


    }



    static class RandomIntRunable implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicInteger.compareAndSet(0, RandomUtils.nextInt(100,200));
        }
    }
}


 class RandomIntCallable implements Callable{

    @Override
    public Object call() throws Exception {
        Thread.sleep(1000);
        return RandomUtils.nextInt(100,200);
    }
}




