package com.lyn.codeLearing.thread.cyclicBarrier;


import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * 多个拦截步骤
 * 多步骤都完成了才进行下一个
 * 内在是个计数器
 */
public class CyclicBarrierTest {

    private static final int THREAD_COUNT=3;

    private static final Random random=new Random();

    private final static CyclicBarrier CYCLIC_BARRIER=new CyclicBarrier(THREAD_COUNT, new Runnable() {
        @Override
        public void run() {
            System.out.println("this activity end , next!!!! ");
        }
    });

    public static void main(String[] args) {
        for(int i =0;i<THREAD_COUNT;i++){
            new Thread("巴巴爸爸"+i+"号"){
                public void run() {
                    try {
                        System.out.println("im thread:" + this.getName() + " 开机，耍起！");
                        CYCLIC_BARRIER.await();
                        System.out.println("im thread:" + this.getName() + " play 文明6通关");
                        Thread.sleep(Math.abs(random.nextInt()) %1000);
                        CYCLIC_BARRIER.await();
                        System.out.println("im thread:" + this.getName() + " play 巫师3通关");
                        CYCLIC_BARRIER.await();
                        System.out.println("im thread:" + this.getName() + " play 仁王通关");
                        CYCLIC_BARRIER.await();
                        System.out.println("im thread:" + this.getName() + " 全通关，睡觉");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

}
