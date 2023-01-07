package com.lyn.codeLearing.thread.countDownLatch;


import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {


    //五名队员
    private final static int GROUP_SIZE=5;

    private final static Random random =new Random();



    private static void processOneGroup(final String groupName) throws InterruptedException {
        //等待队员就绪
        final CountDownLatch preCountDown =new CountDownLatch(GROUP_SIZE);
        final CountDownLatch startCountDown =new CountDownLatch(1);
        //最后结果
        final CountDownLatch endCountDown =new CountDownLatch(GROUP_SIZE);
        System.out.println("分组："+groupName+"开始");
        for(int i=0;i<GROUP_SIZE;i++){
            new Thread(String.valueOf(i)){
                public void run() {
                    preCountDown.countDown();//等待就绪
                    System.out.println("我是线程组"+groupName+"第"+this.getName()+"准备就绪");
                    try {
                        //等待开始指令
                        startCountDown.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    try {
                        Thread.sleep(Math.abs(random.nextInt()) %1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("我是线程组"+groupName+"第"+this.getName()+"执行完成");
                    endCountDown.countDown();
                }
            }.start();
        }

        preCountDown.await();

        System.out.println("ready? go!");

        startCountDown.countDown();

        endCountDown.await();

        System.out.println("end\n-----------------------------------------\n");


    }

    public static void main(String[] args) throws InterruptedException {
        processOneGroup("分组1");
        processOneGroup("分组2");

    }
}
