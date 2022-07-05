package com.lyn.codeLearing.thread.case1;

public class Sample {

    private int number;

    public synchronized void increase() throws Exception {
        while (0 != number) {
            wait();
        }

        number++;

        System.out.println(number);

        notify();
    }

    public synchronized void decrease() throws Exception {
        while (0 == number) {
            wait();
        }

        number--;
        System.out.println(number);

        notify();

    }
}
