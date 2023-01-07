package com.lyn.codeLearing.thread.lockInterface;


import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock和condition级联调用
 */
public class LockConditionCase {

    private static Lock myLock = new ReentrantLock(false);

    private static Condition myCondition = myLock.newCondition();

    /**
     * 实例场景：
     * 定义5个等待线程如下：
     * 线程0：通过await()进行等待。
     * 线程1：通过awaitNanos(long)进行等待，long=1000000000，即1秒钟。
     * 线程2：通过await(long,TimeUnit)进行等待，long=2，TimeUnit=TimeUnit.SECONDS，即2秒钟。
     * 线程3：通过awaitUntil(deadline)进行等待，System.currentTimeMillis() + 5000，即5秒之后的时刻。
     * 线程4：通过awaitUninterruptibly()进行等待。
     * 场景：
     * 等待所有线程自己结束。
     *
     * @param args
     */
    public static void main(String[] args) {
        Thread thread0 = new Thread(() -> {
            myLock.lock();
            System.out.println(Thread.currentThread().getName() + "获得锁");
            try {
                System.out.println(Thread.currentThread().getName() + "调用await");
                myCondition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                myLock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放锁");
            }
        });
        Thread thread1 = new Thread(() -> {
            myLock.lock();
            System.out.println(Thread.currentThread().getName() + "获得锁");
            try {
                System.out.println(Thread.currentThread().getName() + "调用awaitNanos()1秒");
                myCondition.awaitNanos(1000000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                myLock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放锁");
            }
        });
        Thread thread2 = new Thread(() -> {
            myLock.lock();
            System.out.println(Thread.currentThread().getName() + "获得锁");
            try {
                System.out.println(Thread.currentThread().getName() + "调用await(time)2秒");
                myCondition.await(2, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                myLock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放锁");
            }
        });
        Thread thread3 = new Thread(() -> {
            myLock.lock();
            System.out.println(Thread.currentThread().getName() + "获得锁");
            try {
                System.out.println(Thread.currentThread().getName() + "调用awaitUntil(System.currentTimeMillis()+5000)调用后的5秒");
                myCondition.awaitUntil(new Date(System.currentTimeMillis() + 5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "释放锁");
                myLock.unlock();
            }
        });
        Thread thread4 = new Thread(() -> {
            myLock.lock();
            System.out.println(Thread.currentThread().getName() + "获得锁");
            System.out.println(Thread.currentThread().getName() + "调用awaitUninterruptibly调用");
            myCondition.awaitUninterruptibly();
            myLock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放锁");
        });
        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }
}
