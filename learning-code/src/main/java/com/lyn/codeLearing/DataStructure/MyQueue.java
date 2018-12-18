package com.lyn.codeLearing.DataStructure;


import java.util.LinkedList;

/**
 * 队列
 */
public class MyQueue {
    LinkedList list =new LinkedList();

    public void put(Object o){
        list.add(o);
    }

    public Object get(){
        return list.removeFirst();
    }

    public Boolean isEmpty(){
        return list.isEmpty();
    }

    public static void main(String[] args) {
        char a='A';
        System.out.println((char)(a+32));
    }
}
