package com.lyn.codeLearing.reflect;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 使用反射调用类的私有方法
 */
public class PrivateTestMain {

    public static void main(String[] args) throws Exception {
        //---------------------实现PrivateTest------------------------------//
        PrivateTest pt =new PrivateTest();
        Class<?> clazz=pt.getClass();
        // clazz.getMethod() 只能返回public方法
        // clazz.getDeclaredMethod() 返回声明过的方法
        // declared 公告、公然
        Method method =clazz.getDeclaredMethod("sayHello", String.class);
        //压制Java的访问控制检查，可以访问私有方法
        method.setAccessible(true);
        String result = (String) method.invoke(pt,new Object[]{"mdzz"});
        System.out.println(result);
        //---------------------实现PrivateFieldTest-------------------------------//
        System.out.println("---------------------实现PrivateFieldTest-------------------------------");
        PrivateFieldTest pft =new PrivateFieldTest();
        Class<?> clazzPFT=pft.getClass();

        Field field=clazzPFT.getDeclaredField("name");

        //压制java访问私有的检查
        field.setAccessible(true);

        field.set(pft,"www");

        System.out.println(pft.getName());





    }

}
