package com.lyn.codeLearing.annotation;


import java.util.Date;
import java.util.Map;
import java.util.TreeMap;


/**
 * 系统自带注解
 */
public class SystemAnnotationTest {

    @Override //重写
    public String toString() {
        return "this method override";
    }

    @Deprecated //不赞成、弃用
    public static void hello(){

    }


    /**
     *压制语法检查警告，括号里填的就是压制的类型
      可以是数组
     */

    @SuppressWarnings({"unchecked","deprecation"})
    public static void main(String[] args) {
        SystemAnnotationTest test =new SystemAnnotationTest();
        System.out.println(test);

        hello();

        Map map =new TreeMap();

        map.put("hello",new Date());

        System.out.println(map.get("hello"));
    }
}
