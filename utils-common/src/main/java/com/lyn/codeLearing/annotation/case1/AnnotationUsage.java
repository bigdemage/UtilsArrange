package com.lyn.codeLearing.annotation.case1;


/**
 * 自定义注解的使用类
 */

@AnnotationTest(value2 = EnumTest.HELLO)
public class AnnotationUsage {

    public  void method(){
        System.out.println("usage of annotation");
    }

    public static void main(String[] args) {
        AnnotationUsage usage=new AnnotationUsage();

        usage.method();
    }
}
