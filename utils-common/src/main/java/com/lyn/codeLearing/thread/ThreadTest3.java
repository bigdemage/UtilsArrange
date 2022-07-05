package com.lyn.codeLearing.thread;


/**
 * synchronized 同步
 */
public class ThreadTest3 {


    public static void main(String[] args) {
        Example example = new Example();

        Thread t1 = new MyThread(example);
        Thread t2 = new MyThread2(example);

        t1.start();
        t2.start();
    }


}


class Example {

    public   void execute() {
        for (int i = 0; i < 20; i++) {
            synchronized (this){

                System.out.println("hello:" + i);
            }
        }
    }

    public void execute2() {
        for (int i = 0; i < 20; i++) {
            System.out.println("mdzz:" + i);
        }
    }
}

class MyThread extends Thread {

    private Example example;

    public MyThread(Example example) {
        this.example = example;
    }

    @Override
    public void run() {
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.example.execute();
    }
}

class MyThread2 extends Thread {

    private Example example;

    public MyThread2(Example example) {
        this.example = example;
    }

    @Override
    public void run() {
        this.example.execute2();
    }
}
