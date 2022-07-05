package com.lyn.codeLearing.thread.countDownLatch.BianXingJinGang;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ControlConsole {



    //五个位置组成变形金刚
    private static ExecutorService executor = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch downLatch =new CountDownLatch(5);

        executor.execute(new KingElement("头",downLatch));
        executor.execute(new KingElement("左手",downLatch));
        executor.execute(new KingElement("右手",downLatch));
        executor.execute(new KingElement("左脚",downLatch));
        executor.execute(new KingElement("右脚",downLatch));

        System.out.println("开始组装");

        downLatch.await();

        System.out.println("全部组装完毕，GO");

        executor.shutdown();



    }



}
