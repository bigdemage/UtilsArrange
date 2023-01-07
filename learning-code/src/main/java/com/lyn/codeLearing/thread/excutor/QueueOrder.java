package com.lyn.codeLearing.thread.excutor;

import io.netty.util.concurrent.DefaultThreadFactory;
import sun.misc.PostVMInitHook;

import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName QueueOrder
 * @Deacription TODO
 * @Author wrx
 * @Date 2022/3/21/021 17:13
 * @Version 1.0
 **/
public class QueueOrder {

    public static void main(String[] args) throws Exception {
        test();
    }

    public static void test() throws InterruptedException, ExecutionException {

        ThreadPoolExecutor executorService = new ThreadPoolExecutor(2, 3, 30, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(2), new DefaultThreadFactory("test"),
                new ThreadPoolExecutor.DiscardPolicy());

        //每隔两秒打印线程池的信息
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("=====================================thread-pool-info:" + new Date() + "=====================================");
            System.out.println("CorePoolSize:" + executorService.getCorePoolSize());
            System.out.println("PoolSize:" + executorService.getPoolSize());
            System.out.println("ActiveCount:" + executorService.getActiveCount());
            System.out.println("KeepAliveTime:" + executorService.getKeepAliveTime(TimeUnit.SECONDS));
            System.out.println("QueueSize:" + executorService.getQueue().size());
        }, 0, 2, TimeUnit.SECONDS);

        try {
            //同时提交5个任务,模拟达到最大线程数
            for (int i = 0; i < 5; i++) {
                executorService.execute(new Task(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //休眠10秒，打印日志，观察线程池状态
        Thread.sleep(10000);

        //每隔3秒提交一个任务
        AtomicInteger ii=new AtomicInteger(10);
        while (true) {
            Thread.sleep(3000);
            executorService.submit(new Task(ii.getAndIncrement()));
        }
    }

    static class Task implements Runnable {

        private int i;

        public Task(int i){
            this.i=i;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "-执行任务-"+i);
        }
    }
}

