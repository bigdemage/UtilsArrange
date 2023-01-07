package com.lyn.codeLearing.thread.countDownLatch;

import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.*;

/**
 * @ClassName BaseTests
 * @Deacription TODO
 * @Author wrx
 * @Date 2022/3/18/018 15:16
 * @Version 1.0
 **/
public class BaseTests {



    public static void main(String[] args) throws Exception {

        int group =5;

        CountDownLatch zhu=new CountDownLatch(1);

        CountDownLatch fu=new CountDownLatch(group);

        ExecutorService executor= Executors.newCachedThreadPool();


        for(int i=1;i<=group;i++){
            int finalI = i;
            executor.execute(()->{
                try {
                    zhu.await(30, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(finalI +"准备就绪");
                try {
                    Thread.sleep(RandomUtils.nextInt(1000,3000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                fu.countDown();
            });
        }
        //await的时间是如果时长后count还是没变成0，则不再等待，继续执行
        zhu.countDown();
        System.out.println("我出来了");
        fu.await();
        System.out.println("结束");
        executor.shutdown();
    }
}
