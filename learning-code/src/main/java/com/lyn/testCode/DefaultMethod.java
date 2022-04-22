package com.lyn.testCode;

import javax.sound.midi.Soundbank;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public interface DefaultMethod {

     int b=1;

    default String geta(){
        return "aa";
    };


    public static void main(String[] args) {

        Queue queue=new LinkedBlockingQueue();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);

        System.out.println(queue.poll());
        System.out.println("长度"+queue.size());
        System.out.println(queue.peek());
        System.out.println("长度"+queue.size());
        System.out.println(queue.element());
        System.out.println("长度"+queue.size());
        System.out.println(queue.offer(2));
        System.out.println(queue.offer(3));
        System.out.println(queue.offer(5));
    }
}
