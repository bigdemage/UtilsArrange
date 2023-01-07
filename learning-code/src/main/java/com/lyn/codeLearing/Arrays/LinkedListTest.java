package com.lyn.codeLearing.Arrays;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @ClassName LinkedListTest
 * @Deacription TODO
 * @Author wrx
 * @Date 2022/6/21/021 15:03
 * @Version 1.0
 **/
public class LinkedListTest {

    public static void main(String[] args) {

        LinkedBlockingDeque deque=new LinkedBlockingDeque();

        deque.add("啊");
        deque.add("哦");

        System.out.println(deque.peek());
        System.out.println(deque.poll());

        System.out.println(deque.size());

    }
}
