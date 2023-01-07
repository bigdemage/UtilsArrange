package com.lyn.codeLearing.thread.callable;


import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * FutureTask类基本Api
 */
public class FutureTaskApi {

    /**
     * FutureTask类表示一个可以取消的异步计算任务。
     * 这个类提供了对Future接口的简单实现，提供了一些方法：开启计算、取消计算、查询计算是否完成和查询计算结果。
     * 只有计算完成时，才可以通过get()方法获取计算结果；如果计算没有完成，则get()方法会一致在阻塞。
     * 一个FutureTask类可以被用于包装Callable接口或者Runnable接口的实现对象。
     * 因为FutureTask类实现了Runnable接口，所以它可以被提交(submit)给一个Executor接口进行执行。
     * 除了作为一个单独的类使用之外，此类还提供了protected的方法，当创建定制的任务类时，这些方法可能十分有用。
     * <p>
     * 重点！！！：包装了Runnable和Callable,实现了Runnable弥补了Thread不足
     * ---------------------------------------------------------------------------------------------
     */
    private static final Logger LOGGER = Logger.getLogger(FutureTaskApi.class);

    private static final ExecutorService excutor = Executors.newFixedThreadPool(5);

    private static  AtomicInteger ATOMIC_INTEGER=new AtomicInteger(0);

    private static Callable<Integer> intCallable = new Callable<Integer>() {
        @Override
        public Integer call() throws Exception {
            Thread.sleep(2000);
            return RandomUtils.nextInt(100, 200);
        }
    };

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        methodCase1();
        methodCase2();
        methodCase3();
        methodCase4();
        methodCase5();
    }

    //方式1：Future + Callable + ExecutorService
    private static void methodCase1() {
        Future future = excutor.submit(intCallable);
        try {
            System.out.println("Method1::" + future.get());
        } catch (InterruptedException e) {
            LOGGER.info("method1被中断",e);
        } catch (ExecutionException e) {
            LOGGER.info("method1执行异常",e);
        }
    }

    //方式2：FutureTask + Callable + ExecutorService
    private static void methodCase2() {
        FutureTask futureTask =new FutureTask(intCallable);
        excutor.submit(futureTask);
        try {
            System.out.println("Method2::"+futureTask.get());
        } catch (InterruptedException e) {
            LOGGER.info("method2被中断",e);
        } catch (ExecutionException e) {
            LOGGER.info("method2执行异常",e);
        }
    }

    //方式3：FutureTask + Callable + Thread
    private static void methodCase3() {
        FutureTask futureTask=new FutureTask(intCallable);
        new Thread(futureTask).start();
        try {
            System.out.println("Method3::"+futureTask.get());
        } catch (InterruptedException e) {
            LOGGER.info("method3被中断",e);
        } catch (ExecutionException e) {
            LOGGER.info("method3执行异常",e);
        }
    }

    //方式4：FutureTask + Runnable + ExecutorService
    private static void methodCase4() {
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                ATOMIC_INTEGER.compareAndSet(0,RandomUtils.nextInt(100,200));
            }
        };
        FutureTask futureTask=new FutureTask(runnable,ATOMIC_INTEGER);
        excutor.submit(futureTask);
        while(ATOMIC_INTEGER.get()==0);
        System.out.println("Method4::"+ATOMIC_INTEGER.get());
    }

    //方式5：FutureTask + Runnable + Thread
    private static void methodCase5() {
        ATOMIC_INTEGER.set(0);
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                ATOMIC_INTEGER.compareAndSet(0,RandomUtils.nextInt(100,200));
            }
        };
        FutureTask futureTask=new FutureTask(runnable,ATOMIC_INTEGER);
        new Thread(runnable).start();
        while(ATOMIC_INTEGER.get()==0);
        System.out.println("Method5::"+ATOMIC_INTEGER.get());
        excutor.shutdown();
    }
}
