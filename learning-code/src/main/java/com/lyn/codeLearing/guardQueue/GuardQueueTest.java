package com.lyn.codeLearing.guardQueue;

import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName GuardQueueTest
 * @Deacription TODO
 * @Author wrx
 * @Date 2021/9/7/007 15:17
 * @Version 1.0
 **/
public class GuardQueueTest {

    public static void main(String[] args) throws InterruptedException {

        GuardQueue guardQueue = new GuardQueue();

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.execute(() -> {

            System.out.println(guardQueue.get());
        });


        Thread.sleep(2000);

        executorService.execute(() -> {
            guardQueue.put(RandomUtils.nextInt(5, 500));
        });

        executorService.shutdown();
        executorService.awaitTermination(30, TimeUnit.SECONDS);

    }
}
