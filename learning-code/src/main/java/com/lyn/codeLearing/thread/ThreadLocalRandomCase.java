package com.lyn.codeLearing.thread;


import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * ThreadLocalRandom是Java提供的用于并发产生伪随机数的工具类，出现于JDK1.7版本中。

 ThreadLocalRandom可以看做Math.random()的并发升级类。

 ThreadLocalRandom不是直接用new实例化，而是第一次使用其静态方法current()。

 ThreadLocalRandom的优势类似于上章的ThreadLocal：每个线程单独一个变量副本，不再产生数据争用。
 */
public class ThreadLocalRandomCase {


    /**
     * 随机seed(种子)相同和不同
     * Random、ThreadLocalRandom
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
//        for(int i=1;i<=5;i++){
//            TimeUnit.MILLISECONDS.sleep(100);
//            randomDifferentSeed(System.currentTimeMillis());
//        }
//        System.out.println("以上是种子不同的Random类");
//        for(int i=1;i<=5;i++){
//            randomDifferentSeed(1l);
//        }
//        System.out.println("种子相同的Random类");

        //ThreadLocalRandom--种子不同
        System.out.println("ThreadLocalRandom--种子不同");
        Thread randomThread=null;
        for(int i =1;i<=2;i++){
            Thread.sleep(100);
            randomThread=  new Thread(()->{
                ThreadLocalRandom threadLocalRandom =ThreadLocalRandom.current();
                System.out.print(Thread.currentThread().getName()+ ": ");
                for(int j=1;j<=10;j++){
                    System.out.print(threadLocalRandom.nextInt()+"  ");
                }
                System.out.println();
            });
            randomThread.start();
            randomThread.join();
        }
        System.out.println("ThreadLocalRandom--种子不同---END");


        System.out.println("ThreadLocalRandom--种子相同");
        ThreadLocalRandom threadLocalRandom2 =ThreadLocalRandom.current();
        for(int i =1;i<=2;i++){
            Thread.sleep(100);
            randomThread=  new Thread(()->{
                System.out.print(Thread.currentThread().getName()+ ": ");
                for(int j=1;j<=10;j++){
                    System.out.print(threadLocalRandom2.nextInt()+"  ");
                }
                System.out.println();
            });
            randomThread.start();
            randomThread.join();
        }
        System.out.println("ThreadLocalRandom--种子相同---END");





    }

    //Random 种子不同
    private static void randomDifferentSeed(Long time) {
        Random random =new Random(time);
        System.out.println(random.nextInt());
    }


}

