package com.lyn.codeLearing.guardQueue;

import javax.sound.midi.Soundbank;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @ClassName DequeTest
 * @Deacription TODO
 * @Author wrx
 * @Date 2021/11/1/001 10:04
 * @Version 1.0
 **/
public class DequeTest {

    public static void main(String[] args) {


        Deque<Integer> deque=new ArrayDeque();

        for(int i=1;i<10;i++){
            deque.add(i);
        }

        System.out.println(deque.getFirst());
        System.out.println(deque.getLast());
        System.out.println("--------");
        System.out.println(deque.peek());
        System.out.println(deque.poll());
        System.out.println("--------");
        System.out.println(deque.getFirst());
        System.out.println(deque.getLast());

    }
}
