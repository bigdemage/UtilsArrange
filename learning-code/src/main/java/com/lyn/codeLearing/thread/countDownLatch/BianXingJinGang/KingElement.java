package com.lyn.codeLearing.thread.countDownLatch.BianXingJinGang;


import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 金刚的组成
 */
public class KingElement implements Runnable{
    private String name;

    private CountDownLatch downLatch;

    public CountDownLatch getDownLatch() {
        return downLatch;
    }

    public void setDownLatch(CountDownLatch downLatch) {
        this.downLatch = downLatch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public KingElement(String name, CountDownLatch downLatch) {
        this.name = name;
        this.downLatch = downLatch;
    }

    @Override
    public void run() {
        System.out.println("我来组成"+this.name);
        try {
            TimeUnit.MILLISECONDS.sleep(RandomUtils.nextInt(500,2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.downLatch.countDown();
        System.out.println(this.name+"组合完毕");

    }
}
