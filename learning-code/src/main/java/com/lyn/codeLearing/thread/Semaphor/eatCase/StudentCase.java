package com.lyn.codeLearing.thread.Semaphor.eatCase;

import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 场景模拟：
 * 模拟学校食堂的窗口打饭过程
 * 学校食堂有2个打饭窗口
 * 学校中午有20个学生 按次序 排队打饭
 * 每个人打饭时耗费时间不一样
 * 有的学生耐心很好，他们会一直等待直到打到饭
 * 有的学生耐心不好，他们等待时间超过了心里预期，就不再排队，而是回宿舍吃泡面了
 * 有的学生耐心很好，但是突然接到通知，说是全班聚餐，所以也不用再排队，而是去吃大餐了
 */

public class StudentCase {

    //食堂的两个窗口
    private static final Semaphore eatWindow = new Semaphore(2, true);

    public static void main(String[] args) throws InterruptedException {

        implementModel1();//一种方式




    }


    private static void implementModel1() throws InterruptedException {
        for (int i = 1; i <= 20; i++) {
            Thread.sleep(50);
            final int ii = i;
            switch (RandomUtils.nextInt(0, 3)) {
                case 0://耐心好的学生，等待打饭，打到饭随机吃完
                    new Thread(() -> {
                        try {
                            //阻塞的获取，一直阻塞到获取为止
                            eatWindow.acquireUninterruptibly();
                            System.out.println(ii + "同学排到了窗口，打饭中...");
                            TimeUnit.MILLISECONDS.sleep(RandomUtils.nextInt(1000, 5000));
                            System.out.println(ii + "同学打饭完毕，吃起...");
                            System.out.println("----------------------------------------");
                            eatWindow.release();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }).start();
                    break;
                case 1://耐心不好，等待时间超过预期，就不排了，回宿舍
                    new Thread(() -> {
                        try {
                            long longtime = RandomUtils.nextLong(100, 1000);
                            System.out.println("我是" + ii + "同学，我脾气很差");
                            boolean flag = eatWindow.tryAcquire(longtime, TimeUnit.MILLISECONDS);
                            if (!flag) {
                                System.out.println(ii + "同学" + longtime + "毫秒过去了，不排了");
                            }else {
                                System.out.println("卧槽，排到了，吃起");
                                eatWindow.release();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }).start();
                    break;
                case 2://耐心很好的一批，但是突然通知回宿舍
                    Thread thread2 = new Thread(() -> {
                        try {
                                eatWindow.acquire();
                                TimeUnit.MILLISECONDS.sleep(RandomUtils.nextLong(2000,5000));
                                eatWindow.release();
                        } catch (InterruptedException e) {
                            System.out.println(ii + "来通知了，都回去有大餐，不要在这吃了");
                        }
                    });
                    thread2.start();
                    Thread.sleep(RandomUtils.nextLong(10, 1600));
                    thread2.interrupt();
                    break;
            }
        }


    }
}
