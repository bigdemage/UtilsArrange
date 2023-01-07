package com.lyn.codeLearing.thread;

public class MyThreadPrinter2 implements Runnable{

    private String name;
    private Object prev;
    private Object self;

    private MyThreadPrinter2(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
       synchronized (prev){
           System.out.println(1);
       }
    }

    public static void main(String[] args) throws Exception {



        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        MyThreadPrinter2 pa = new MyThreadPrinter2("A", c, a);
        MyThreadPrinter2 pc = new MyThreadPrinter2("C", c, c);

        new Thread(pa).start();
        new Thread(pc).start();




    }
}
