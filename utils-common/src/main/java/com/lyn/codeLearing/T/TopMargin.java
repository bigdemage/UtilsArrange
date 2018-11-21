package com.lyn.codeLearing.T;


import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 泛型上边界
 */
public class TopMargin<T> {

    private final static Logger LOGGER=Logger.getLogger(TopMargin.class);

    static class A{

    }

    static class B extends A{

    }

    static class C extends  B{

    }

    static class D extends C{

    }

    static class E extends D{

    }

    public static void main(String[] args) {
        List<? extends B> list =new ArrayList<C>();

        list  = Arrays.asList(new C(),new D(),new E());


        System.out.println("-----> "+list.get(0).getClass().getName().toString());
        System.out.println("-----> "+list.get(1).getClass().getName().toString());
        System.out.println("-----> "+list.get(2).getClass().getName().toString());

        LOGGER.info("上边界类型通配符（<? extends>）：因为可以确定最大类型，所以可以以最大类型去获取数据。但是不能写入数据。\n");


    }

}




