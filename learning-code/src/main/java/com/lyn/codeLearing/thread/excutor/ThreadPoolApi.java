package com.lyn.codeLearing.thread.excutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 各种线程池
 */
public class ThreadPoolApi {

    public static void main(String[] args) {
        //并行工作者线程池，已jvm可运行的cpu核数作为可并行数量
        ExecutorService executor = Executors.newWorkStealingPool();

        for(int i=1;i<=20;i++){
            executor.submit(()->{
                System.out.println(Thread.currentThread());
            });
        }

    }
}
