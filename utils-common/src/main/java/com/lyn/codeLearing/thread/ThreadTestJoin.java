package com.lyn.codeLearing.thread;

public class ThreadTestJoin extends Thread{

    private String name;

    public ThreadTestJoin(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"线程开始");
        for(int i=0;i<5;i++){
            try {
                sleep((int)Math.random()*10);
                System.out.println(Thread.currentThread().getName()+"子线程"+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"线程结束");

    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("主线程"+Thread.currentThread().getName()+" begin");

        Thread thread1 =new  ThreadTestJoin("A");
        Thread thread2 =new  ThreadTestJoin("B");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("主线程"+Thread.currentThread().getName()+" end");

    }

}
