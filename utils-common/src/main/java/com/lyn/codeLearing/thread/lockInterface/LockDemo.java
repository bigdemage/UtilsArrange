package com.lyn.codeLearing.thread.lockInterface;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    //定义一个非公平的锁
    private static Lock lock = new ReentrantLock(false);

    /**
     * <p>Lock接口方法学习</p>
     *
     * @author hanchao 2018/3/18 13:54
     **/
    public static void main(String[] args) throws InterruptedException {
        //线程0一直持有锁5000毫秒
        new Thread(() -> {
            System.out.println("线程[" + Thread.currentThread().getName() + "]尝试获取锁");
            lock.lock();
            System.out.println("线程[" + Thread.currentThread().getName() + "]获取了锁...");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {//在finally代码块中是否锁
                lock.unlock();
                System.out.println("线程[" + Thread.currentThread().getName() + "]释放了锁..");
            }
        }).start();
        Thread.sleep(10);
        //线程1通过lock.lock()持续去尝试获取锁
        new Thread(() -> {
            System.out.println("线程[" + Thread.currentThread().getName() + "]通过lock.lock()持续去尝试获取锁");
            lock.lock();
            System.out.println("线程[" + Thread.currentThread().getName() + "]获取了锁...");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {//在finally代码块中是否锁
                lock.unlock();
                System.out.println("线程[" + Thread.currentThread().getName() + "]释放了锁..");
            }
        }).start();
        //线程2通过lock.tryLock()尝试去获取一次锁
        new Thread(() -> {
            System.out.println("线程[" + Thread.currentThread().getName() + "]通过lock.tryLock()尝试去获取一次锁");
            if (lock.tryLock()) {
                System.out.println("线程[" + Thread.currentThread().getName() + "]获取了锁...");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println("线程[" + Thread.currentThread().getName() + "]释放了锁..");
                }
            } else {
                System.out.println("线程[" + Thread.currentThread().getName() + "]尝试获取锁失败，不再等待.");
            }
        }).start();
        //线程3通过lock.tryLock(long,TimeUnit)尝试在一定时间内去获取锁
        new Thread(() -> {
            System.out.println("线程[" + Thread.currentThread().getName() + "]通过lock.tryLock(long,TimeUnit)尝试在一定时间内去获取锁");
            try {
                if (lock.tryLock(2, TimeUnit.SECONDS)) {
                    System.out.println("线程[" + Thread.currentThread().getName() + "]获取了锁...");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                        System.out.println("线程[" + Thread.currentThread().getName() + "]释放了锁..");
                    }
                } else {
                    System.out.println("线程[" + Thread.currentThread().getName() + "]在指定时间内没有获取到锁，不再等待.");
                }
            } catch (InterruptedException e) {
                //e.printStackTrace(); 被中断时会产生的意料之中的错误，无需打印
                System.out.println("线程[" + Thread.currentThread().getName() + "]被thread.interrupt()中断，不在尝试去获取锁");
            }
        }).start();
        //线程4通过lock.lockInterruptibly()尝试可中断的去获取锁
        Thread thread4 = new Thread(() -> {
            try {
                System.out.println("线程[" + Thread.currentThread().getName() + "]通过lock.lockInterruptibly()尝试可中断的去获取锁");
                lock.lockInterruptibly();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                    System.out.println("线程[" + Thread.currentThread().getName() + "]释放了锁..");
                }
            } catch (InterruptedException e) {
                //e.printStackTrace(); 被中断时会产生的意料之中的错误，无需打印
                System.out.println("线程[" + Thread.currentThread().getName() + "]被thread.interrupt()中断，不在尝试去获取锁");
            }
        });
        thread4.start();
        Thread.sleep(3000);
        thread4.interrupt();
    }

}
