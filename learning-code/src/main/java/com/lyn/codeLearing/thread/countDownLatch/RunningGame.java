package com.lyn.codeLearing.thread.countDownLatch;


import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CountDowmLatch小用例
 * 跑步比赛，10位运动员，1个裁判，吹响哨子同时出发
 */
public class RunningGame {

    private static ExecutorService executors=Executors.newFixedThreadPool(10);
    private static CountDownLatch downLatch =new CountDownLatch(1);


    public static void main(String[] args) throws InterruptedException {
            for(int i=1;i<=10;i++){
                int finalI = i;
                executors.submit(()->{
                    try {
                        System.out.println("选手"+ finalI +"号准备就绪");
                        downLatch.await();
                        TimeUnit.MILLISECONDS.sleep(RandomUtils.nextInt(500,5000));
                        System.out.println("选手"+finalI+"到达终点");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println("各就各位开始!");
            downLatch.countDown();
            //比赛完了关闭
            TimeUnit.MILLISECONDS.sleep(10000);
            executors.shutdown();
    }


}
