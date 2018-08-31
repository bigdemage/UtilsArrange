package com.lyn.codeLearing.thread.Semaphor;


import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用wait和notify实现Semaphor
 */
public class NotifyAchieveSemaphor {

    private static final int QUERY_MAX_THREAD = 5; //允许最多进入线程数

    private static final int START_THREAD = 20;//初始进入线程数

    private static final AtomicInteger NOW_CALL_COUNT = new AtomicInteger(0);//线程安全的计数器

    private static final Object LOCK_OBJECT = new Object(); //线程对象锁

    private static final Random random =new Random(); //随机数


    public static void main(String[] args) {
        for (int i = 0; i < START_THREAD; i++) {
            new Thread(String.valueOf(i)) {
                public void run() {
                    try{
                        //检查是否获得访问权限
                        tryToLock();
                        System.out.println("我是线程："+this.getName()+"获得了执行权");
                        Thread.sleep(random.nextInt() & 1000);
                        System.out.println("线程："+this.getName()+"end!!!");
                        //结束时释放访问权限
                        toUnLock();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }.start();
        }


    }

    /**
     * 执行结束后解锁
     */
    private static void toUnLock() {
        NOW_CALL_COUNT.getAndDecrement();//递减
        synchronized (LOCK_OBJECT){
            LOCK_OBJECT.notify();
        }
    }

    /**
     * 获取线程执行权限，上锁
     */
    private static void tryToLock() throws InterruptedException {
        int nowValue =NOW_CALL_COUNT.get();
        int tryTimes =0;

        while(true){
            //当前进入线程数量小于规定线程数量
            if(nowValue< QUERY_MAX_THREAD && NOW_CALL_COUNT.compareAndSet(nowValue,nowValue+1)){
                break; //获得锁，跳出循环
            }
            if(tryTimes % 3==0){ //尝试三次都没有获得锁，就先等待

                waitForObjectNotify();
            }

            nowValue=NOW_CALL_COUNT.get();
            tryTimes++;
        }
    }


    private static void waitForObjectNotify() throws InterruptedException {

        synchronized (LOCK_OBJECT){
            LOCK_OBJECT.wait(1000); //等到1秒后重试
        }
    }
}
