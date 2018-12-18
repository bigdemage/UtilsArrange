package com.lyn.codeLearing.thread.callable;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.lyn.codeLearing.thread.FetchMoney;
import org.apache.log4j.Logger;

import javax.sound.midi.Soundbank;
import java.util.concurrent.*;

/**
 * Future类的基本API
 */
public class FutureBaseApi {

    private static final Logger LOGGER=Logger.getLogger(FutureBaseApi.class);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService excutor = Executors.newCachedThreadPool();

        Callable<Integer> callableInt =new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                try {

                    Thread.sleep(5000);
                }catch (InterruptedException e){
                    System.out.println("正在计算被取消......");
                }
                return 100;
            }
        };
        Future<Integer> future=excutor.submit(callableInt);


        Thread.sleep(1000);

        System.out.println("是否被取消？"+future.isCancelled());
        System.out.println("是否已完成？"+future.isDone());


        /**
         * 关于参数解释：
         * 当创建了Future实例，任务可能有以下三种状态：
             等待状态。此时调用cancel()方法不管传入true还是false都会标记为取消，任务依然保存在任务队列中，但当轮到此任务运行时会直接跳过。
             完成状态。此时cancel()不会起任何作用，因为任务已经完成了。
             运行中。此时传入true会中断正在执行的任务，传入false则不会中断
           Future.cancel(true)适用于：
             1. 长时间处于运行的任务，并且能够处理interruption
           Future.cancel(false)适用于：
             1. 未能处理interruption的任务
             2. 不清楚任务是否支持取消
             3. 需要等待已经开始的任务执行完成
         */
        System.out.println("cancel方法返回的状态"+future.cancel(false));

        Thread.sleep(1000);

        System.out.println("调用cancel后是否已完成?"+future.isDone());
        System.out.println("调用cancel后是否已取消?"+future.isCancelled());

        //再次调用，然后2秒是尝试去获取
        Future future2=excutor.submit(callableInt);

        try {
            future2.get(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            LOGGER.info("获取中但线程被中断",e);
        } catch (TimeoutException e) {
            LOGGER.info("时间到了但没获取数据",e);
        } catch (ExecutionException e){
            LOGGER.info("执行出异常",e);
        }

        System.out.println("超时获取后是否被取消？"+future.isCancelled());
        System.out.println("超时获取后是否已完成？"+future.isDone());


        try {
            System.out.println(future2.get());
        } catch (InterruptedException e) {
            LOGGER.info("阻塞继续获取中但线程被中断",e);
        } catch (ExecutionException e){
            LOGGER.info("阻塞继续获取执行出异常",e);
        }

        System.out.println("阻塞继续获取后是否被取消？"+future.isCancelled());
        System.out.println("阻塞继续获取超时获取后是否已完成？"+future.isDone());

        excutor.shutdown();


    }

}
