package com.lyn.codeLearing.thread.Semaphor;


import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * 线程量控制
 * 例如有20个线程发起请求，但对于此类线程只允许5个进入访问，访问结束后才允许继续进入
 */
public class SemaphorTest {
    //只允许5个
    private final static Semaphore MAX_SEMA_PHORE = new Semaphore(5);

    private final static Random random = new Random();

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            final int  num =i;
            new Thread() {
                public void run() {
                    boolean flag = false;
                    try {
                        MAX_SEMA_PHORE.acquire();
                        flag = true;
                        System.out.println("i'm thread:" + num + " comming!!!!");
                        Thread.sleep(1000+(random.nextInt() & 5000));
                        System.out.println("i'm thread:" + num + " 执行结束!!!!");
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (flag) {
                            MAX_SEMA_PHORE.release();
                        }
                    }
                }
            }.start();
        }
    }
}
