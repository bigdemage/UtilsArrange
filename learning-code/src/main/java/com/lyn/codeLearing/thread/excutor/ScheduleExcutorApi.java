package com.lyn.codeLearing.thread.excutor;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.*;

/**
 * 练习内容：
 * 分别实现以下调度任务：
 * 方式1：通过schedule方法实现：2秒之后打印系统时间。
 * 方式2：通过scheduleWithFixedDelay方法实现：5秒之后开始周期性的打印系统时间，连续两次打印间隔为3秒(delay)，每次打印耗时2秒。
 * 方式3：通过scheduleAtFixedRate方法实现：5秒之后开始周期性的打印系统时间，每3秒(period)打印一次，每次打印耗时2秒。
 * 方式4：通过scheduleAtFixedRate方法实现：5秒之后开始周期性的打印系统时间，每2秒(period)打印一次，每次打印耗时3秒
 */
public class ScheduleExcutorApi {

    private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);

    private static int type = 1;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         *  scheduleWithFixedDelay和scheduleAtFixedRate区别：
         *  1：scheduleAtFixedRate参数中如果线程消耗时间小于方法参数delay时间，则周期为delay时间
         *  2：scheduleAtFixedRate参数中如果线程消耗时间大于方法参数delay时间，则周期为程序的消耗时间
         *  3：scheduleWithFixedDelay周期为方法delay+程序消耗时间
         *  */
        //     mode4();//方式4
        //     mode1();//方式1
               mode2();//方式2
        //     mode3();//方式3
    }

    private static void mode4() {
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            }
        }, 5, 2, TimeUnit.SECONDS);
    }

    private static void mode3() {

        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("我是mode3:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            }
        }, 5, 3, TimeUnit.SECONDS);

    }

    private static void mode2() {
        executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("我是mode2:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            }
        }, 5, 3, TimeUnit.SECONDS);
    }

    private static void mode1() throws ExecutionException, InterruptedException {
        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
            }
        }, 2, TimeUnit.SECONDS);

        ScheduledFuture scheduledFuture = executorService.schedule(() -> {
            return "alibaba";
        }, 2, TimeUnit.SECONDS);

        System.out.println(scheduledFuture.get());

        executorService.shutdown();
    }

}
