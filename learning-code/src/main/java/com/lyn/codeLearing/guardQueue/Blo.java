package com.lyn.codeLearing.guardQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Blo {


    public static void main(String[] args) throws InterruptedException {

        BlockingQueue <Integer> queue=new ArrayBlockingQueue(10);


        queue.add(1);

        queue.add(2);

        queue.add(3);

        System.out.println(queue.take());

        System.out.println(queue);
    }

}




