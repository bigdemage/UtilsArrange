package com.lyn.codeLearing.thread.cyclicBarrier;


import ch.qos.logback.core.net.SyslogOutputStream;
import ch.qos.logback.core.util.TimeUtil;
import org.apache.commons.lang3.RandomUtils;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 循环栅栏 又名：【人满发车】
 * cyclicbarrier基本API示范
 * await(),线程等待数量超过方数，则继续执行
 */
public class CyclicbarrierCase {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    private static final int type=5;

    private static final int arraySize=5000;

    private static   int[] results =new int[5];

    private static ExecutorService executors=Executors.newFixedThreadPool(5);


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        switch (type) {
            case 1:
                awaitCase();//await方法case
                break;
            case 2:
                resetCase();//reset方法case
                break;
            case 3:
                isBrokenCase();//isBroken方法case
                break;
            case 4:
                cyclicBarrierMean();//屏障的意义
            case 5:
                calculationCase();//用例场景1
        }

    }

    /**
     * 模拟多线程分组计算
       有一个大小为5000的随机数组，用5个线程分别计算1000个元素的和
       然后在将计算结果进行合并，得出最后的结果。
     */
    private static void calculationCase() throws ExecutionException, InterruptedException {
        //构造的第二个参数：屏障线程的运行时机：等待的线程数量=parties之后，CyclicBarrier打开屏障之前。
        CyclicBarrier cyclicBarrierD =new CyclicBarrier(5,()->{
            int finalint=0;
            for(int i=0;i<results.length;i++){
                finalint+=results[i];
            }

            System.out.println("多线程计算结果："+finalint);
            executors.shutdown();

        });
        int[] ints =new int[arraySize];
        for(int i=0;i<arraySize;i++){
            ints[i]= RandomUtils.nextInt(100,1000);
        }
        int finalResult=0;
        for(int i=0;i<arraySize;i++){
            finalResult+=ints[i];
        }
        System.out.println("单线程计算结果： "+finalResult);


        for(int i=0;i<=4;i++){
            final int i1=i;
            executors.submit(()->{
                int index_start=i1*1000;
                int index_end=i1*1000+999;
                results[i1] =0;
                for(int j =index_start;j<=index_end;j++){
                    results[i1]+=ints[j];
                }
                try {
                    System.out.println("等待的线程数："+cyclicBarrierD.getNumberWaiting());
                    cyclicBarrierD.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }

    }

    private static void cyclicBarrierMean() {
        CyclicBarrier cyclicBarrierC =new CyclicBarrier(2,()->{
            System.out.println("栅栏准备活动开始");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("栅栏准备完毕，放开栅栏");
        });

        for(int i=1;i<=2;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"准备准备开始干活了,等栅栏放开");
                try {
                    cyclicBarrierC.await();
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName()+"线程被中断");
                } catch (BrokenBarrierException e) {
                    System.out.println(Thread.currentThread().getName()+"线程被重置");
                }
                System.out.println(Thread.currentThread().getName()+"栅栏已经放开，do some thing..");
            }).start();
        }

    }

    private static void isBrokenCase() throws InterruptedException {
        CyclicBarrier cyclicBarrierB =new CyclicBarrier(3);
        System.out.println("初始后的broken："+cyclicBarrier.isBroken());

        new Thread(()->{
            try {
                System.out.println("启动一个等待线程，无超时时间");
                cyclicBarrierB.await();
            } catch (InterruptedException e) {
                System.out.println("11中断异常");
            } catch (BrokenBarrierException e) {
                System.out.println("11重置异常");
            }

        }).start();
        Thread.sleep(200);
        System.out.println("无超时时间等待线程的broken："+cyclicBarrierB.isBroken());


        new Thread(()->{
            try {
                System.out.println("启动一个等待线程,等待1秒后超时");
                cyclicBarrierB.await(500,TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                System.out.println("中断异常");
            } catch (BrokenBarrierException e) {
                System.out.println("22重置异常");
            } catch (TimeoutException e) {
                System.out.println("22超时异常");
            }
        }).start();

        TimeUnit.SECONDS.sleep(2);
        System.out.println("现在的等待线程数："+cyclicBarrierB.getNumberWaiting());

        System.out.println("超时后的broken："+cyclicBarrierB.isBroken());

        cyclicBarrierB.reset();
        System.out.println("重置后的broken："+cyclicBarrierB.isBroken()+" 等待线程数："+cyclicBarrierB.getNumberWaiting());

    }


    private static void resetCase() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for(int i=1;i<=2;i++){
            executorService.execute(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+"准备等待");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName()+"被中断");
                } catch (BrokenBarrierException e) {
                    System.out.println(Thread.currentThread().getName()+"被重置");
                }
            });
        }
        Thread.sleep(500);
        System.out.println("调用cyclicBarrier.reset()");
        cyclicBarrier.reset();

        executorService.execute(()->{
            try {
                System.out.println("alili准备等待");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                System.out.println("alili被中断");
            } catch (BrokenBarrierException e) {
                System.out.println("线程在等待过程中被重置则抛出此异常，继续执行");
            }
        });

        Thread.sleep(500);
        System.out.println("再次调用cyclicBarrier.reset()");
        cyclicBarrier.reset();

        executorService.shutdown();
    }


    private static void awaitCase() throws InterruptedException {
        System.out.println("开启屏障的方数:" + cyclicBarrier.getParties());

        new Thread(() -> {
            System.out.println("开启1号线程");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("1号线程已终止");
        }).start();

        Thread.sleep(100);

        System.out.println("1号线程开启后，被屏障的线程数" + cyclicBarrier.getNumberWaiting());

        TimeUnit.MILLISECONDS.sleep(100);

        new Thread(() -> {
            System.out.println("开启2号线程");
            try {
                cyclicBarrier.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("2号线程已终止");
        }).start();

        Thread.sleep(100);


        System.out.println("2号线程开启后，被屏障的线程数" + cyclicBarrier.getNumberWaiting());

        Thread.sleep(100);

        System.out.println("屏障打开后再次添加线程");
        new Thread(() -> {
            System.out.println("开启3号线程");
            try {
                cyclicBarrier.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("3号线程已终止");
        }).start();
        Thread.sleep(100);


        System.out.println("3号线程开启后，被屏障的线程数" + cyclicBarrier.getNumberWaiting());

        TimeUnit.MILLISECONDS.sleep(100);

        new Thread(() -> {
            System.out.println("开启4号线程");
            try {
                cyclicBarrier.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("4号线程已终止");
        }).start();
    }


}
