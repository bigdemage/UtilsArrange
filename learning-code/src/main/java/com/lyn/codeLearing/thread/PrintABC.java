package com.lyn.codeLearing.thread;


import java.util.concurrent.TimeUnit;

/**
 * 建立三个线程，A线程打印10次A，B线程打印10次B,C线程打印10次C，要求线程同时运行，交替打印10次ABC
 */
public class PrintABC extends Thread {

    private String name;

    private Object pre;
    private Object self;

    public PrintABC(String name, Object pre, Object self) {
        this.name = name;
        this.pre = pre;
        this.self = self;
    }


    @Override
    public void run() {
        int count = 10;
        while (count > 0) {
            synchronized (pre) {
                synchronized (self) {
                    System.out.println(this.name);
                    count--;
                    self.notify();
                }
                try {
                    pre.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object A = new Object();
        Object B = new Object();
        Object C = new Object();

        Thread aThread = new PrintABC("A", C, A);
        Thread bThread = new PrintABC("B", A, B);
        Thread cThread = new PrintABC("C", B, C);

        aThread.start();
        Thread.sleep(100);
        bThread.start();
        Thread.sleep(100);
        cThread.start();
        Thread.sleep(100);


    }
}
