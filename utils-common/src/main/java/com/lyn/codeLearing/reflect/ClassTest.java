package com.lyn.codeLearing.reflect;

public class ClassTest {

    public static void main(String[] args) {
        Class<?> classType=Child.class;
        System.out.println(classType);

        //获取父类
        classType=classType.getSuperclass();

        System.out.println(classType);
        //获取父类
        classType=classType.getSuperclass();

        System.out.println(classType);

        classType=classType.getSuperclass();
        //最顶层没有父类，所有是null
        System.out.println(classType);
    }


}

class Parent{

}

class Child extends Parent{

}
