package com.lyn.codeLearing.thread.excutor;


import jdk.nashorn.internal.codegen.CompilerConstants;

import java.util.concurrent.*;

/**
 * Executors基础api
 */
public class ExcecutorsApi {

    private static ExecutorService executor = Executors.newCachedThreadPool();

    /**
     * Executors提供了一下几种类型方法：
     * 第1类静态方法：将Runnable转换成Callable--这个方法有问题。
     * 第2类静态方法：线程工厂(ThreadFactory)类。
     * 第3类静态方法：实例化几类不可配置的线程池(ExecutorService和ScheduleExecutorService)。
     * 第4类静态方法：实例化几类预先配置的常用线程池(ExecutorService)。
     * 第5类静态方法：实例化几类预先配置的常用可调度线程池(ScheduleExecutorService)。
     * @param args
     */
    public static void main(String[] args) throws Exception {
//        apiType1();
//        apiType2();
          apiType3();
    }


    private static void apiType3() {


    }

    private static void apiType2() throws ExecutionException, InterruptedException {
        ThreadFactory factory =Executors.defaultThreadFactory();
        for(int i=1;i<=10;i++){
            factory.newThread(()->{
                System.out.println(Thread.currentThread());
            }).start();
        }

    }


    private static void apiType1() throws Exception {
        final Integer[] i = {null};
        Runnable run = new Runnable() {
            @Override
            public void run() {
                i[0] = 2;
            }
        };
//        Callable call0 =Executors.callable(run);
//        Future future0=executor.submit(call0);
//        System.out.println(future0.get());


        Callable call = Executors.callable(run, i[0]);


        Future<Integer> future = (Future<Integer>) call.call();

        System.out.println(future.get());

        executor.shutdown();


    }
}
