package com.lyn.codeLearing.thread.caseWaitNotify;


import ch.qos.logback.core.util.TimeUtil;
import org.apache.commons.lang3.RandomUtils;

import javax.validation.constraints.Min;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * 厨师炒6盘菜就要休息，还剩2盘就继续炒
 */
public class CookThread {

    //食物
    private static Queue<String> foods = new LinkedList<>();
    //最多6盘
    private static int MAX_SIZE = 6;
    //2盘时提醒厨师做菜
    private static int MIN_SIZE = 2;

    private static String[] foodType = {"白菜", "花菜", "羊肉", "牛肉", "汤"};


    /**
     * 添加菜
     *
     * @param foods
     */
    public static void addFood(Queue<String> foods) {
        String cai = foodType[RandomUtils.nextInt(0, 4)];
        foods.add(cai);
        System.out.println("厨师做了一盘:" + cai + ",现在有" + foods);
    }


    /**
     * 厨师炒菜
     */
    public static class Cooker implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (foods) {
                    if (foods.size() == MAX_SIZE) {
                        try {
                            System.out.println("菜满了厨师休息会...");
                            foods.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        addFood(foods);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Cooker()).start();

        TimeUnit.SECONDS.sleep(2);

        new Thread(new Consumer()).start();
    }


    /**
     * 消费菜
     */
    public static class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (foods) {
                    if (foods.size() > 0) {
                        if (foods.size() == MIN_SIZE) {
                            System.out.println("还剩两盘菜通知厨师做菜");
                            foods.notify();
                        }
                        System.out.println("消费了一盘:" + foods.poll() + "还有" + foods);
                        try {
                            //吃的时间
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }
    }


}
