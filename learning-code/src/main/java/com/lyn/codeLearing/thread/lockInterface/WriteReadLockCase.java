package com.lyn.codeLearing.thread.lockInterface;


import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * 0 写写互斥
 * 1 写读互斥
 * 2 读写互斥
 * 3 读读共享
 */
public class WriteReadLockCase {

    private static ReentrantReadWriteLock myLock = new ReentrantReadWriteLock();
    //读锁
    private static ReentrantReadWriteLock.ReadLock readLock = myLock.readLock();
    //写锁
    private static ReentrantReadWriteLock.WriteLock writeLock = myLock.writeLock();

    private static String msg = "锤飞你医保卡!";

    private static final int type = 3;

    /**
     * 0 写写互斥
     * 1 写读互斥
     * 2 读写互斥
     * 3 读读共享
     *
     * @param args
     */
    public static void main(String[] args) {

        switch (type) {
            case 0:
                new Thread(() -> {
                    System.out.println(Thread.currentThread().getName() + "尝试获取写锁");
                    writeLock.lock();
                    System.out.println(Thread.currentThread().getName() + "获得写锁");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        writeLock.unlock();
                        System.out.println(Thread.currentThread().getName() + "解开写锁");
                    }
                }).start();
                new Thread(() -> {
                    System.out.println(Thread.currentThread().getName() + "尝试获取写锁");
                    writeLock.lock();
                    System.out.println(Thread.currentThread().getName() + "获得写锁");
                    try {
                        TimeUnit.MILLISECONDS.sleep(1800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        writeLock.unlock();
                        System.out.println(Thread.currentThread().getName() + "解开写锁");
                    }
                }).start();
                break;

            case 1:
                new Thread(() -> {
                    System.out.println(Thread.currentThread().getName() + "尝试获取写锁");
                    writeLock.lock();
                    System.out.println(Thread.currentThread().getName() + "获得写锁,开始修改数据");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        writeLock.unlock();
                        System.out.println(Thread.currentThread().getName() + "解开写锁");
                    }
                }).start();
                new Thread(() -> {
                    System.out.println(Thread.currentThread().getName() + "尝试获取读锁");
                    readLock.lock();
                    System.out.println(Thread.currentThread().getName() + "获得读锁");
                    try {
                        TimeUnit.MILLISECONDS.sleep(1800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        readLock.unlock();
                        System.out.println(Thread.currentThread().getName() + "解开读锁");
                    }
                }).start();
                break;
            //读写互斥
            case 2:
                new Thread(() -> {
                    System.out.println(Thread.currentThread().getName() + "尝试获取读锁");
                    readLock.lock();
                    System.out.println(Thread.currentThread().getName() + "获得读锁");
                    try {
                        TimeUnit.MILLISECONDS.sleep(2800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        readLock.unlock();
                        System.out.println(Thread.currentThread().getName() + "解开读锁");
                    }
                }).start();
                new Thread(() -> {
                    System.out.println(Thread.currentThread().getName() + "尝试获取写锁");
                    writeLock.lock();
                    System.out.println(Thread.currentThread().getName() + "获得写锁");
                    try {
                        TimeUnit.MILLISECONDS.sleep(1800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        writeLock.unlock();
                        System.out.println(Thread.currentThread().getName() + "解开写锁");
                    }
                }).start();

                break;
                //读读共享
            case 3:
                new Thread(() -> {
                    System.out.println(Thread.currentThread().getName() + "尝试获取读锁");
                    readLock.lock();
                    System.out.println(Thread.currentThread().getName() + "获得读锁");
                    try {
                        TimeUnit.MILLISECONDS.sleep(2800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        readLock.unlock();
                        System.out.println(Thread.currentThread().getName() + "解开读锁");
                    }
                }).start();
                new Thread(() -> {
                    System.out.println(Thread.currentThread().getName() + "尝试获取读锁");
                    readLock.lock();
                    System.out.println(Thread.currentThread().getName() + "获得读锁");
                    try {
                        System.out.println(msg);
                    } finally {
                        readLock.unlock();
                        System.out.println(Thread.currentThread().getName() + "解开读锁");
                    }
                }).start();
                break;
        }


    }
}
