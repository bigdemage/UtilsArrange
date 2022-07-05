package com.lyn.codeLearing.annotation.case2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 用反射找注解
 */
public class MyReflection {

    public static void main(String[] args) throws Exception {
        MyTest myTest =new MyTest();

        Class<MyTest> clazz=MyTest.class;

        Method method=clazz.getDeclaredMethod("output",new Class[]{});

        /*
            判断方法上是否有此注解
            并且注解是要RUNTIME类型！！
        */

        if (method.isAnnotationPresent(MyAnnotation.class)){

            method.invoke(myTest,new Object[]{});

            MyAnnotation myAnnotation=method.getAnnotation(MyAnnotation.class);

            System.out.println(myAnnotation.hello());
        }

        /**
         * 获取方法上所有的注解，也得是RUNTIME类型！
         */
        Annotation[] annotations=method.getAnnotations();

        System.out.println(annotations.length);
    }
}
