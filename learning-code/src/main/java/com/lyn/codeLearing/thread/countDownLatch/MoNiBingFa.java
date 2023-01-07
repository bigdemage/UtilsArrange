package com.lyn.codeLearing.thread.countDownLatch;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @ClassName MoNiBingFa
 * @Deacription TODO
 * @Author wrx
 * @Date 2022/4/24/024 14:03
 * @Version 1.0
 **/
@Slf4j
public class MoNiBingFa {

    //机器的cpu个数
    private static final int cpuCount=Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) throws InterruptedException {
        int SZIE=7000;

        log.info("机器cpu个数：{}",cpuCount);
//        ThreadPoolExecutor executor= new ThreadPoolExecutor(cpuCount+1,
//                cpuCount*2,
//                60L,
//                TimeUnit.SECONDS,·
//                new LinkedBlockingQueue<>(7000)
//                );

        ExecutorService executor=Executors.newFixedThreadPool(200);


        CountDownLatch startC=new CountDownLatch(SZIE);

        CountDownLatch endC=new CountDownLatch(SZIE);

        long start=System.currentTimeMillis();

        for(int i=1;i<=SZIE;i++){
            int finalI = i;
            executor.submit(()->{
                try {
                    log.info(finalI +"进来");
                    endC.await();
                    Thread.sleep(100);
                    log.info(finalI+"执行完");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    startC.countDown();
                }
            });
            endC.countDown();
        }
        log.info("循环完毕");
        startC.await();

        log.info("执行时间:{}ms",(System.currentTimeMillis()-start));
    }
}
