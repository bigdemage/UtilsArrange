package com.lyn.codeLearing.T;


import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 泛型下边界
 */
public class LowerMargin<T> {

    private final static Logger LOGGER =Logger.getLogger(LowerMargin.class);


    static class A {

    }

    static class B extends A {

    }

    static class C extends B {
        @Override
        public String toString() {
            return "C";
        }
    }

    static class D extends C {
        @Override
        public String toString() {
            return "D";
        }
    }

    static class E extends D {
        @Override
        public String toString() {
            return "E";
        }
    }

    public static void main(String[] args) {
        List<? super B> list = new ArrayList<A>();

        list.add(new C());

        list.add(new D());

        list.add(new E());

        System.out.println("-----> " + list.get(0).toString());
        System.out.println("-----> " + list.get(1).toString());
        System.out.println("-----> " + list.get(2).toString());
        System.out.println("-----> " + list);

        LOGGER.info("下边界类型通配符（<? super>）：因为可以确定最小类型，所以可以以最小类型去写入数据。但是不能获取数据。\n");


    }

}




