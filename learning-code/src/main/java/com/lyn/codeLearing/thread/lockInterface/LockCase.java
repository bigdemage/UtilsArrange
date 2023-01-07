package com.lyn.codeLearing.thread.lockInterface;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock类案例
 */
public class LockCase {

    private static Lock myLock = new ReentrantLock(false);

    public static void main(String[] args) throws InterruptedException {

        //5秒后解锁
        Thread thread0 = new Thread(() -> {
            myLock.lock();
            System.out.println(Thread.currentThread().getName() + "获得锁");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "释放锁");

                myLock.unlock();
            }
        });
        Thread thread1 = new Thread(() -> {
            myLock.lock();
            System.out.println(Thread.currentThread().getName() + "获得锁");

            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "释放锁");

                myLock.unlock();
            }
        });
        Thread thread2 = new Thread(() -> {
            if (myLock.tryLock()) {
                System.out.println(Thread.currentThread().getName() + "获得锁");
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + "释放锁");
                    myLock.unlock();
                }
            } else {
                System.out.println(Thread.currentThread().getName() + "尝试获取锁失败");
            }
        });

        Thread thread3 = new Thread(() -> {
            try {
                if (myLock.tryLock(2, TimeUnit.SECONDS)) {
                    System.out.println(Thread.currentThread().getName() + "获得锁");
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println(Thread.currentThread().getName() + "释放锁");
                        myLock.unlock();
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("线程[" + Thread.currentThread().getName() + "]被thread.interrupt()中断，不在尝试去获取锁");

            }
        });

        Thread thread4 = new Thread(() -> {
            try {
                myLock.lockInterruptibly();
                System.out.println(Thread.currentThread().getName() + "获得锁");
                try {
                    TimeUnit.MILLISECONDS.sleep(10);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + "释放锁");
                    myLock.unlock();
                }
            } catch (InterruptedException e) {
                System.out.println("线程[" + Thread.currentThread().getName() + "]被thread.interrupt()中断，不在尝试去获取锁");
            }
        });

        thread0.start();
        TimeUnit.MILLISECONDS.sleep(10);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        TimeUnit.SECONDS.sleep(3);
        thread4.interrupt();

    }

}
