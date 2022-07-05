package com.lyn.testCode;

import java.util.ArrayList;
import java.util.List;

public class FinalTest {

    public static void main(String[] args) {
        new FinalTest().test();
    }

    public void test(){
        int a =100;
        new A(){

            @Override
            public void display() {
                System.out.println(a);
            }
        };
    }

    static abstract class A {
        public A() {
            display();
        }

        public abstract void display();
    }

    static class B extends A {

        private int INT = 100;
        private final int FINAL_INT = 100;
        private final Integer FINAL_INTEGER = 100;
        private String STR = "wang";
        private final String FINAL_STR = "wang";
        private final String FINAL_STR1 = new String("wang");
        private final List<String> list = new ArrayList<String>();

        public B() {
            super();
            System.out.println("----------------------------");
            System.out.println("B:wang");
            display();
        }

        public void display() {
            System.out.println(INT);
            System.out.println(FINAL_INT);
            System.out.println(FINAL_INTEGER);
            System.out.println(STR);
            System.out.println(FINAL_STR);
            System.out.println(FINAL_STR1);
            System.out.println(list);
        }
    }
}


