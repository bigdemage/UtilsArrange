package com.lyn.codeLearing.thread;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;

import java.util.concurrent.*;

public class CallableTest implements Callable {
    private static final Logger LOGGER=Logger.getLogger(CallableTest.class);

    @Override
    public Object call() throws Exception {

        TimeUnit.SECONDS.sleep(2);

        return Thread.currentThread().getName();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService e = Executors.newCachedThreadPool();
        Future<?> future =e.submit(new CallableTest());
        try{
            Long begin = System.currentTimeMillis();
            LOGGER.info("future开始执行任务...当前时间：" + begin);
            LOGGER.info("通过future.isDone()判断任务是否计算完成：" + future.isDone());
            LOGGER.info("通过future.isCancelled()判断任务是否取消：" + future.isCancelled());
            LOGGER.info("通过future.get()获取任务的计算结果(从任务开始就一直等待，直至有返回值)：" + future.get());
            LOGGER.info("future结束执行任务...共计用时：" + (System.currentTimeMillis() - begin) + "ms..\n");

        }finally {
            System.out.println(future.isCancelled());
            System.out.println(future.isDone());
            e.shutdown();
        }

    }
}
