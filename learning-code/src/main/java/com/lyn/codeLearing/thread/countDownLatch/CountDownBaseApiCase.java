package com.lyn.codeLearing.thread.countDownLatch;

import sun.java2d.SurfaceDataProxy;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch基础ApiCase
 */
public class CountDownBaseApiCase {

    private static final int num =10;

    /**
     *   线程          等待方法               终止原因
     * ------------------------------------------------
     * thread-0     await()                 count=0
     * thread-1     await()                 interrupted
     * ------------------------------------------------
     * thread-2     await(timeout,TimeUnit)    timeout
     * thread-3     await(timeout,TimeUnit)    interrupted
     * thread-4     await(timeout,TimeUnit)    count=0
     */
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch =new CountDownLatch(num);
        //await直到count=0
        new Thread(()->{
            try {
                System.out.println("await-thread-0 will await");
                countDownLatch.await();
                System.out.println("awit-thread-0 countDownLatch is "+countDownLatch.getCount());
            } catch (InterruptedException e) {
                System.out.println("await-thread-0 is interrupt");
            }
        }).start();
        //终止原因interrupted
        Thread thread1 =new Thread(() -> {
            try {
                System.out.println("await-thread-1 will await");
                countDownLatch.await();
            } catch (InterruptedException e) {
                System.out.println("await-thread-1 is interrupt");
            }
        });
        thread1.start();

        new Thread(()->{
            try {
                System.out.println("await-thread-2 will await");
                //flag为true时说明在wait时间还没到时就count已经变成了0
                boolean flag =countDownLatch.await(15, TimeUnit.SECONDS);
                if(flag){
                    System.out.println("awit-thread-2 countDownLatch 时间未到 is "+countDownLatch.getCount());
                }else{
                    System.out.println("awit-thread-2 await 时间到了 countdownLatch is "+ countDownLatch.getCount());
                }
            } catch (InterruptedException e) {
                System.out.println("await-thread-2 is interrupt");
            }
        }).start();

        Thread thread3 =new Thread(()->{
            try {
                System.out.println("await-thread-3 will await");
                boolean flag =countDownLatch.await(5, TimeUnit.SECONDS);
                if(flag){
                    System.out.println("awit-thread-3 count is 0");
                }else{
                    System.out.println("awit-thread-3 await 时间到了countDownLatch is "+countDownLatch.getCount());
                }
            } catch (InterruptedException e) {
                System.out.println("await-thread-3 is interrupt");
            }
        });
        thread3.start();
        new Thread(()->{
            try {
                System.out.println("await-thread-4 will await");
                if(countDownLatch.await(5, TimeUnit.SECONDS)){
                    System.out.println("awit-thread-4 await时间未到 countDownLatch is "+countDownLatch.getCount());
                }else{
                    System.out.println("awit-thread-4 await时间到了 countDownLatch is "+countDownLatch.getCount());
                }
            } catch (InterruptedException e) {
                System.out.println("await-thread-4 is interrupt");
            }
        }).start();
        TimeUnit.MILLISECONDS.sleep(2500);
        thread1.interrupt();
        thread3.interrupt();



        while (countDownLatch.getCount()>0){
            TimeUnit.MILLISECONDS.sleep(500);
            countDownLatch.countDown();
        }


    }
}
