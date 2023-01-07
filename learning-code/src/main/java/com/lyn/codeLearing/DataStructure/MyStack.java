package com.lyn.codeLearing.DataStructure;


import java.util.LinkedList;

/**
 * 用LinkedList实现栈
 */
public class MyStack {
    LinkedList list =new LinkedList();

    public void put(Object o){
        list.add(o);
    }

    public Object get(){
        return list.removeLast();
    }

    public Boolean isEmpty(){
        return list.isEmpty();
    }

    public static void main(String[] args) {
        MyStack myStack =new MyStack();
        myStack.put("www");
        myStack.put("qqq");
        myStack.put("eee");
        myStack.put("rrr");

        System.out.println(myStack.get());
        System.out.println(myStack.get());
        System.out.println(myStack.get());
        System.out.println(myStack.get());

        System.out.println(myStack.isEmpty());
    }
}
