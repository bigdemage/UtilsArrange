package com.lyn.codeLearing.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;


/**
 * 双冒号的使用方法：
 * 对象::实例方法
 * 类::静态方法
 * 类::实例方法
 */
public class AcceptMethod {

    public static void  printValur(String str){
        System.out.println("print value : "+str);
    }

    public static void main(String[] args) {
        List<String> al = Arrays.asList("a","b","c","d");

        //写法1
        for (String a: al) {
            AcceptMethod.printValur(a);
        }
        //写法2
        al.forEach(x->{
            AcceptMethod.printValur(x);
        });
        //写法3
        al.forEach(AcceptMethod::printValur);

        //写法4

        Consumer<String> consumer = AcceptMethod::printValur;
        al.forEach(str->consumer.accept(str));


    }
}
